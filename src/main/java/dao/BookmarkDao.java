// 3. BookmarkDao.java
package dao;

import dto.Bookmark;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {

    // 북마크 데이터 저장 메서드
    public int addBookmark(Long wifiId, Long bookmarkGroupId) {
        String query = "INSERT INTO bookmark (group_id, wifi_id, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, bookmarkGroupId);
            pstmt.setLong(2, wifiId);

            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding bookmark: " + e.getMessage());
        }

        return rowsAffected;
    }

    // 모든 북마크 데이터 조회 메서드
    public List<Bookmark> getAllBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        String query = "SELECT b.id, b.group_id, g.group_name, w.name AS wifi_name, b.created_at " +
                "FROM bookmark b " +
                "INNER JOIN wifi_info w ON b.wifi_id = w.id " +
                "INNER JOIN bookmark_group g ON b.group_id = g.id " +
                "ORDER BY b.created_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setBookmarkId(rs.getInt("id"));
                bookmark.setGroupId(rs.getInt("group_id"));
                bookmark.setGroupName(rs.getString("group_name"));
                bookmark.setWifiName(rs.getString("wifi_name"));
                bookmark.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                bookmarks.add(bookmark);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching bookmarks: " + e.getMessage());
        }

        return bookmarks;
    }

    // 특정 ID의 북마크 조회 메서드
    public Bookmark getBookmarkById(Long id) {
        Bookmark bookmark = null;
        String query = "SELECT b.id, b.group_id, g.group_name, w.name AS wifi_name, b.created_at " +
                "FROM bookmark b " +
                "INNER JOIN wifi_info w ON b.wifi_id = w.id " +
                "INNER JOIN bookmark_group g ON b.group_id = g.id " +
                "WHERE b.id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bookmark = new Bookmark();
                    bookmark.setBookmarkId(rs.getInt("id"));
                    bookmark.setGroupId(rs.getInt("group_id"));
                    bookmark.setGroupName(rs.getString("group_name"));
                    bookmark.setWifiName(rs.getString("wifi_name"));
                    bookmark.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching bookmark by ID: " + e.getMessage());
        }

        return bookmark;
    }

    // 북마크 삭제 메서드
    public int deleteBookmark(Long id) {
        String query = "DELETE FROM bookmark WHERE id = ?";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting bookmark: " + e.getMessage());
        }

        return rowsAffected;
    }
}
