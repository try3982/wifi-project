package dao;

import dto.DistanceCalculator;
import dto.History;
import dto.WifiInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {

    // 데이터베이스에 WiFi 데이터를 저장하는 메서드
    public int save(List<WifiInfo> wifiList) {
        String sql = "INSERT INTO wifi_info (distance, manage_num, district, name, road_address, detail_address, floor, type, agency, service_type, connection, install_year, indoor, wifi_env, latitude, longitude, work_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE " +
                "distance = VALUES(distance), manage_num = VALUES(manage_num), district = VALUES(district), name = VALUES(name), " +
                "road_address = VALUES(road_address), detail_address = VALUES(detail_address), floor = VALUES(floor), " +
                "type = VALUES(type), agency = VALUES(agency), service_type = VALUES(service_type), connection = VALUES(connection), " +
                "install_year = VALUES(install_year), indoor = VALUES(indoor), wifi_env = VALUES(wifi_env), " +
                "latitude = VALUES(latitude), longitude = VALUES(longitude), work_date = VALUES(work_date)";
        int savedCount = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int batchSize = 0;

            for (WifiInfo wifi : wifiList) {
                pstmt.setDouble(1, wifi.getDistance()); // distance 값
                pstmt.setString(2, wifi.getManageNum());
                pstmt.setString(3, wifi.getDistrict());
                pstmt.setString(4, wifi.getName());
                pstmt.setString(5, wifi.getRoadAddress());
                pstmt.setString(6, wifi.getDetailAddress());
                pstmt.setString(7, wifi.getFloor());
                pstmt.setString(8, wifi.getType());
                pstmt.setString(9, wifi.getAgency());
                pstmt.setString(10, wifi.getServiceType());
                pstmt.setString(11, wifi.getConnection());
                pstmt.setInt(12, wifi.getInstallYear());
                pstmt.setString(13, wifi.getIndoor());
                pstmt.setString(14, wifi.getWifiEnv());
                pstmt.setDouble(15, wifi.getLatitude());
                pstmt.setDouble(16, wifi.getLongitude());
                pstmt.setTimestamp(17, wifi.getWorkDate() != null ? Timestamp.valueOf(wifi.getWorkDate()) : null);

                pstmt.addBatch();
                batchSize++;

                // 500개마다 배치 실행
                if (batchSize % 500 == 0) {
                    int[] results = pstmt.executeBatch();
                    savedCount += results.length;
                    System.out.println("500개의 WiFi 데이터 저장 완료.");
                }
            }

            // 남아있는 배치 실행
            int[] results = pstmt.executeBatch();
            savedCount += results.length;

        } catch (SQLException e) {
            System.err.println("WiFi 데이터 저장 중 오류 발생: " + e.getMessage());
        }

        return savedCount;
    }

    // 사용자의 위치를 기준으로 가장 가까운 WiFi 데이터를 가져오는 메서드
    public List<WifiInfo> findNearestWifi(double userLat, double userLng) {
        String sql = "SELECT *, " +
                "(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(latitude)) * COS(RADIANS(longitude) - RADIANS(?)) + SIN(RADIANS(?)) * SIN(RADIANS(latitude)))) AS distance " +
                "FROM wifi_info " +
                "ORDER BY distance ASC LIMIT 20";

        List<WifiInfo> wifiList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, userLat);
            pstmt.setDouble(2, userLng);
            pstmt.setDouble(3, userLat);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WifiInfo wifi = new WifiInfo();
                    wifi.setManageNum(rs.getString("manage_num"));
                    wifi.setDistrict(rs.getString("district"));
                    wifi.setName(rs.getString("name"));
                    wifi.setRoadAddress(rs.getString("road_address"));
                    wifi.setDetailAddress(rs.getString("detail_address"));
                    wifi.setFloor(rs.getString("floor"));
                    wifi.setType(rs.getString("type"));
                    wifi.setAgency(rs.getString("agency"));
                    wifi.setLatitude(rs.getDouble("latitude"));
                    wifi.setLongitude(rs.getDouble("longitude"));
                    wifi.setDistance(rs.getDouble("distance"));
                    wifiList.add(wifi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("근처 WiFi 정보를 가져오는 동안 오류 발생: " + e.getMessage());
        }

        return wifiList;
    }

    // 히스토리 데이터를 가져오는 메서드
    public List<History> getHistory() {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT id, latitude, longitude, query_time FROM history ORDER BY id DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setLatitude(rs.getDouble("latitude"));
                history.setLongitude(rs.getDouble("longitude"));
                history.setQueryTime(rs.getTimestamp("query_time").toLocalDateTime());
                historyList.add(history);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving history: " + e.getMessage());
        }

        return historyList;
    }
}