package dao;

import dto.WifiInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WifiDao {

    /**
     * WiFi 정보를 데이터베이스에 저장하는 메서드
     *
     * @param wifiList 저장할 WiFi 정보 리스트
     * @return 저장 성공 여부
     */
    public int saveWifiData(List<WifiInfo> wifiList) {
        String query = "INSERT INTO wifi_info (distance, manage_num, district, name, road_address, detail_address, " +
                "floor, type, agency, service_type, connection, install_year, indoor, wifi_env, latitude, longitude, work_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int totalInserted = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (WifiInfo wifi : wifiList) {
                statement.setDouble(1, wifi.getDistance());
                statement.setString(2, wifi.getManageNum());
                statement.setString(3, wifi.getDistrict());
                statement.setString(4, wifi.getName());
                statement.setString(5, wifi.getRoadAddress());
                statement.setString(6, wifi.getDetailAddress());
                statement.setString(7, wifi.getFloor());
                statement.setString(8, wifi.getType());
                statement.setString(9, wifi.getAgency());
                statement.setString(10, wifi.getServiceType());
                statement.setString(11, wifi.getConnection());
                statement.setInt(12, wifi.getInstallYear());
                statement.setString(13, String.valueOf(wifi.getIndoor()));
                statement.setString(14, wifi.getWifiEnv());
                statement.setDouble(15, wifi.getLatitude());
                statement.setDouble(16, wifi.getLongitude());
                statement.setString(17, wifi.getWorkDate());

                statement.addBatch(); // Batch 처리로 성능 최적화
                totalInserted++;
            }

            statement.executeBatch(); // Batch 실행
        } catch (SQLException e) {
            System.err.println("데이터 저장 중 오류 발생: " + e.getMessage());
        }

        return totalInserted;
    }
}
