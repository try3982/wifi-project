package dao;

import dto.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    /**
     * 새로운 히스토리를 데이터베이스에 저장
     *
     * @param history 저장할 히스토리 객체
     */
    public void saveHistory(History history) {
        String query = "INSERT INTO history (latitude, longitude) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, history.getLatitude());
            pstmt.setDouble(2, history.getLongitude());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("히스토리 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 모든 히스토리를 데이터베이스에서 조회
     *
     * @return 히스토리 리스트
     */
    public List<History> getAllHistories() {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT id, latitude, longitude, created_at FROM history ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                History history = new History();
                history.setId(rs.getLong("id"));
                history.setLatitude(rs.getDouble("latitude"));
                history.setLongitude(rs.getDouble("longitude"));

                // 데이터베이스의 문자열을 LocalDateTime으로 변환
                String timestamp = rs.getString("created_at");
                if (timestamp != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime createdAt = LocalDateTime.parse(timestamp, formatter);
                    history.setCreatedAt(createdAt);
                }

                historyList.add(history);
            }

        } catch (SQLException e) {
            System.err.println("히스토리 조회 중 오류 발생: " + e.getMessage());
        }

        return historyList;
    }
}
