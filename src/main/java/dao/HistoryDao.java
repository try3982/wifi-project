package dao;

import dto.History;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    // 히스토리 저장 메서드
    public int saveHistory(History history) {
        String query = "INSERT INTO history (latitude, longitude, query_time, remarks) VALUES (?, ?, ?, ?)";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDouble(1, history.getLatitude());
            pstmt.setDouble(2, history.getLongitude());
            pstmt.setTimestamp(3, Timestamp.valueOf(history.getQueryTime()));
            pstmt.setString(4, history.getRemarks());

            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("히스토리 저장 중 오류 발생: " + e.getMessage());
        }

        return rowsAffected;
    }

    // 히스토리 목록 조회 메서드
    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT id, latitude, longitude, query_time, remarks FROM history ORDER BY query_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
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
            System.err.println("히스토리 목록 조회 중 오류 발생: " + e.getMessage());
        }

        return historyList;
    }

    // 히스토리 삭제 메서드
    public int deleteHistoryById(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, id);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("히스토리 삭제 중 오류 발생: " + e.getMessage());
        }

        return rowsAffected;
    }

}
