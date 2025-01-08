package api;

import com.google.gson.*;
import dto.WifiInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiWifiFetcher {
    private static final String API_KEY = "566444644b747279353274426e4848";
    private static final String BASE_API_URL = "http://openapi.seoul.go.kr:8088/";

    /**
     * API 데이터를 가져오는 메서드
     * @param format 데이터 형식 (json 또는 xml)
     * @param dataset 데이터셋 이름 (예: TbPublicWifiInfo)
     * @param start 시작 인덱스
     * @param end 종료 인덱스
     * @return API 응답 문자열
     * @throws Exception 요청 실패 시 예외 발생
     */
    public String fetchData(String format, String dataset, int start, int end) throws Exception {
        String fullURL = BASE_API_URL + API_KEY + "/" + format + "/" + dataset + "/" + start + "/" + end + "/";
        StringBuilder response = new StringBuilder();

        // HTTP 연결
        URL url = new URL(fullURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        // 응답 코드 확인
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            throw new Exception("API 요청 실패: HTTP 응답 코드 " + responseCode);
        }

        conn.disconnect();
        return response.toString();
    }

    /**
     * JSON 데이터를 파싱하여 WiFi 정보 리스트로 변환
     * @param responseBody API 응답 JSON 문자열
     * @return WiFi 정보 리스트
     */
    public List<WifiInfo> parseJsonResponse(String responseBody) {
        Gson gson = new Gson();
        List<WifiInfo> wifiList = new ArrayList<>();

        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        JsonObject tbPublicWifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
        JsonArray rows = tbPublicWifiInfo.getAsJsonArray("row");

        for (JsonElement element : rows) {
            JsonObject wifiInfo = element.getAsJsonObject();
            WifiInfo wifi = new WifiInfo();

            wifi.setManageNum(wifiInfo.get("X_SWIFI_MGR_NO").getAsString());
            wifi.setDistrict(wifiInfo.get("X_SWIFI_WRDOFC").getAsString());
            wifi.setName(wifiInfo.get("X_SWIFI_MAIN_NM").getAsString());
            wifi.setRoadAddress(wifiInfo.get("X_SWIFI_ADRES1").getAsString());
            wifi.setDetailAddress(wifiInfo.get("X_SWIFI_ADRES2").getAsString());
            wifi.setFloor(wifiInfo.get("X_SWIFI_INSTL_FLOOR").getAsString());
            wifi.setType(wifiInfo.get("X_SWIFI_INSTL_TY").getAsString());
            wifi.setLatitude(wifiInfo.get("LAT").getAsDouble());
            wifi.setLongitude(wifiInfo.get("LNT").getAsDouble());

            wifiList.add(wifi);
        }

        return wifiList;
    }
}
