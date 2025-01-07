package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // MariaDB 서버 정보
    private static final String DB_URL = "jdbc:mariadb://172.16.117.131:3306/public_wifi";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    // 데이터베이스 연결을 반환하는 메서드
    public static Connection getConnection() throws SQLException {
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MariaDB JDBC Driver를 찾을 수 없습니다.");
            e.printStackTrace();
            throw new SQLException("MariaDB Driver가 로드되지 않았습니다.");
        }

        // 데이터베이스 연결
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
