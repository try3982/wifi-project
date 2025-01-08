package dao;

import com.google.gson.*;
import dto.WifiInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WifiApiFetcher {
    private static final String API_KEY = "566444644b747279353274426e4848";
    private static final String BASE_API_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/";

    public List<WifiInfo> fetchAllWifiData() throws Exception {
        int start = 1;
        int end = 1000; // 한 번에 가져올 데이터 개수
        List<WifiInfo> wifiList = new ArrayList<>();

        while (true) {
            String url = BASE_API_URL + start + "/" + end + "/";
            String response = fetchData(url);

            // JSON 파싱
            List<WifiInfo> partialList = parseJsonResponse(response);
            wifiList.addAll(partialList);

            // 종료 조건: 더 이상 데이터가 없을 경우
            if (partialList.size() < 1000) break;

            // 다음 범위 설정
            start += 1000;
            end += 1000;
        }

        return wifiList;
    }

    private String fetchData(String urlString) throws Exception {
        StringBuilder response = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return response.toString();
    }

    private List<WifiInfo> parseJsonResponse(String responseBody) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        JsonArray rows = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
        List<WifiInfo> wifiList = new ArrayList<>();

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
            wifi.setAgency(wifiInfo.get("X_SWIFI_INSTL_MBY").getAsString());
            wifi.setLatitude(wifiInfo.get("LAT").getAsDouble());
            wifi.setLongitude(wifiInfo.get("LNT").getAsDouble());
            wifiList.add(wifi);
        }

        return wifiList;
    }
}
