package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WifiApi {
    private static final int PAGE_SIZE = 1000; // 한 번에 요청할 데이터 개수
    private static final String BASE_URL = "http://openapi.seoul.go.kr:8088/566444644b747279353274426e4848/json/TbPublicWifiInfo/";

    /**
     * Open API에서 WiFi 정보를 가져옵니다.
     *
     * @param pageIndex 페이지 번호 (0부터 시작)
     * @return JSON 형식의 응답 데이터
     */
    public String fetchWifiData(int pageIndex) {
        int startIndex = pageIndex * PAGE_SIZE + 1; // 시작 페이지
        int endIndex = (pageIndex + 1) * PAGE_SIZE; // 종료 페이지

        System.out.println("Fetching data for range: " + startIndex + " ~ " + endIndex);

        // API URL 구성
        String apiUrl = BASE_URL + startIndex + "/" + endIndex;

        StringBuilder jsonResponse = new StringBuilder();

        try {
            // HTTP GET 요청
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 연결 타임아웃
            connection.setReadTimeout(5000);    // 읽기 타임아웃

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        jsonResponse.append(inputLine);
                    }
                }
            } else {
                System.err.println("Failed to fetch data. HTTP Response Code: " + responseCode);
            }

            connection.disconnect(); // 연결 종료
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Data fetching complete for range: " + startIndex + " ~ " + endIndex);

        return jsonResponse.toString(); // JSON 응답 반환
    }
}
