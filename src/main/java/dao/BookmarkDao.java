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
    public int addBookmark(String name, int order) {
        String query = "INSERT INTO bookmark (group_name, sort_order, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, order);

            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("북마크 추가 오류 " + e.getMessage());
        }

        return rowsAffected;
    }

    public int addBookGroup(int groupId, int wifiId) {
        String query = "INSERT INTO bookmark (group_id, wifi_id) VALUES (?, ?)";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, wifiId);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("북마크 추가 오류: " + e.getMessage());
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
            System.err.println("북마크 추가 오류: " + e.getMessage());
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
            System.err.println("북마크 id 에러 : " + e.getMessage());
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
            System.err.println("북마크 삭제 오류 " + e.getMessage());
        }

        return rowsAffected;
    }


}
