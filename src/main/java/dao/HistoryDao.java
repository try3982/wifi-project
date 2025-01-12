package dao;

import dto.History;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    public int saveHistory(History history) {
        String query = "INSERT INTO history (latitude, longitude, query_time, remarks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, history.getLatitude());
            pstmt.setDouble(2, history.getLongitude());
            pstmt.setTimestamp(3, Timestamp.valueOf(history.getQueryTime()));
            pstmt.setString(4, history.getRemarks());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT id, latitude, longitude, query_time, remarks FROM history ORDER BY query_time DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setLatitude(rs.getDouble("latitude"));
                history.setLongitude(rs.getDouble("longitude"));
                history.setQueryTime(rs.getTimestamp("query_time").toLocalDateTime());
                history.setRemarks(rs.getString("remarks"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    public int deleteHistoryById(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
