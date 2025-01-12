package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dto.WifiInfo;
import dao.WifiDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WifiApiFetcher {

    private static final String API_KEY = "566444644b747279353274426e4848";
    private static final String BASE_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/";

    public List<WifiInfo> fetchAllWifiData() {
        List<WifiInfo> wifiList = new ArrayList<>();
        int start = 1;

        while (true) {
            int end = start + 999;
            String url = BASE_URL + start + "/" + end + "/";
            String jsonResponse = fetchData(url);

            if (jsonResponse == null) {
                System.out.println("API로부터 응답을 받지 못했습니다.");
                break;
            }

            List<WifiInfo> partialList = parseWifiData(jsonResponse);
            System.out.println(partialList.size() + "개의 데이터를 가져왔습니다. (시작: " + start + ", 끝: " + end + ")");

            wifiList.addAll(partialList);

            if (partialList.isEmpty()) {
                System.out.println("더 이상 가져올 데이터가 없습니다.");
                break;
            }

            start = end + 1;
        }

        System.out.println("총 " + wifiList.size() + "개의 WiFi 데이터를 가져왔습니다.");
        return wifiList;
    }

    private String fetchData(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP 응답 코드: " + responseCode); // 응답 코드 출력

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
            } else {
                System.err.println("API 요청 실패. 응답 코드: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("API 호출 중 오류 발생: " + e.getMessage());
        }
        System.out.println("API 응답 내용: " + response.toString()); // 응답 내용 출력
        return response.toString();
    }



    private List<WifiInfo> parseWifiData(String jsonResponse) {
        List<WifiInfo> wifiList = new ArrayList<>();
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject == null || !jsonObject.has("TbPublicWifiInfo")) {
            System.err.println("API 응답에 'TbPublicWifiInfo' 데이터가 없습니다.");
            return wifiList;
        }

        JsonObject dataObject = jsonObject.getAsJsonObject("TbPublicWifiInfo");

        if (!dataObject.has("row")) {
            System.err.println("'TbPublicWifiInfo'에 'row' 데이터가 없습니다.");
            return wifiList;
        }

        JsonArray rows = dataObject.getAsJsonArray("row");

        if (rows != null) {
            rows.forEach(row -> {
                JsonObject wifiObject = row.getAsJsonObject();
                WifiInfo wifi = new WifiInfo();

                try {
                    wifi.setDistance(wifiObject.has("distance") ? wifiObject.get("distance").getAsDouble() : null);
                    wifi.setManageNum(wifiObject.get("X_SWIFI_MGR_NO").getAsString());
                    wifi.setDistrict(wifiObject.get("X_SWIFI_WRDOFC").getAsString());
                    wifi.setName(wifiObject.get("X_SWIFI_MAIN_NM").getAsString());
                    wifi.setRoadAddress(wifiObject.get("X_SWIFI_ADRES1").getAsString());
                    wifi.setDetailAddress(wifiObject.get("X_SWIFI_ADRES2").getAsString());
                    wifi.setFloor(wifiObject.has("X_SWIFI_INSTL_FLOOR") ? wifiObject.get("X_SWIFI_INSTL_FLOOR").getAsString() : null);
                    wifi.setType(wifiObject.has("X_SWIFI_INSTL_TY") ? wifiObject.get("X_SWIFI_INSTL_TY").getAsString() : null);
                    wifi.setAgency(wifiObject.has("X_SWIFI_INSTL_MBY") ? wifiObject.get("X_SWIFI_INSTL_MBY").getAsString() : null);
                    wifi.setServiceType(wifiObject.has("X_SWIFI_SVC_SE") ? wifiObject.get("X_SWIFI_SVC_SE").getAsString() : null);
                    wifi.setConnection(wifiObject.has("X_SWIFI_CMCWR") ? wifiObject.get("X_SWIFI_CMCWR").getAsString() : null);
                    wifi.setInstallYear(wifiObject.has("X_SWIFI_CNSTC_YEAR") ? wifiObject.get("X_SWIFI_CNSTC_YEAR").getAsInt() : 0);
                    wifi.setIndoor(wifiObject.has("X_SWIFI_INOUT_DOOR") ? wifiObject.get("X_SWIFI_INOUT_DOOR").getAsString() : null);
                    wifi.setWifiEnv(wifiObject.has("X_SWIFI_REMARS3") ? wifiObject.get("X_SWIFI_REMARS3").getAsString() : null);
                    wifi.setLatitude(wifiObject.get("LAT").getAsDouble());
                    wifi.setLongitude(wifiObject.get("LNT").getAsDouble());
                    wifi.setWorkDate(wifiObject.has("WORK_DTTM") ? wifiObject.get("WORK_DTTM").getAsString() : null);

                    wifiList.add(wifi);
                } catch (Exception e) {
                    System.err.println("파싱 중 오류 발생: " + e.getMessage());
                }
            });
        }

        return wifiList;
    }

    public int saveWifiDataToDatabase() {
        WifiDao wifiDao = new WifiDao();
        List<WifiInfo> wifiList = fetchAllWifiData();
        int savedCount = wifiDao.save(wifiList);
        System.out.println("총 " + savedCount + "개의 WiFi 데이터를 데이터베이스에 저장했습니다.");
        return savedCount;
    }

    public String fetchAndDisplayWifiData() {
        int savedCount = saveWifiDataToDatabase();
        return "총 " + savedCount + "개의 와이파이 정보를 저장했습니다.";
    }

    public static void main(String[] args) {
        WifiApiFetcher fetcher = new WifiApiFetcher();
        String resultMessage = fetcher.fetchAndDisplayWifiData();
        System.out.println(resultMessage);
    }
}