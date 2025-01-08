package dao;

import dto.WifiInfo;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class WifiXmlParser {
    public static List<WifiInfo> parse(String xmlData) throws Exception {
        List<WifiInfo> wifiList = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));

        NodeList wifiNodes = doc.getElementsByTagName("row");
        for (int i = 0; i < wifiNodes.getLength(); i++) {
            Element row = (Element) wifiNodes.item(i);

            WifiInfo wifi = new WifiInfo();
            wifi.setManageNum(getTagValue(row, "X_SWIFI_MGR_NO"));
            wifi.setDistrict(getTagValue(row, "X_SWIFI_WRDOFC"));
            wifi.setName(getTagValue(row, "X_SWIFI_MAIN_NM"));
            wifi.setRoadAddress(getTagValue(row, "X_SWIFI_ADRES1"));
            wifi.setDetailAddress(getTagValue(row, "X_SWIFI_ADRES2"));
            wifi.setFloor(getTagValue(row, "X_SWIFI_INSTL_FLOOR"));
            wifi.setType(getTagValue(row, "X_SWIFI_INSTL_TY"));
            wifi.setAgency(getTagValue(row, "X_SWIFI_INSTL_MBY"));
            wifi.setServiceType(getTagValue(row, "X_SWIFI_SVC_SE"));
            wifi.setConnection(getTagValue(row, "X_SWIFI_CMCWR"));
            wifi.setInstallYear(parseInt(getTagValue(row, "X_SWIFI_CNSTC_YEAR")));
            wifi.setIndoor(parseInt(getTagValue(row, "X_SWIFI_INOUT_DOOR")));
            wifi.setWifiEnv(getTagValue(row, "X_SWIFI_REMARS3"));
            wifi.setLatitude(parseDouble(getTagValue(row, "LAT")));
            wifi.setLongitude(parseDouble(getTagValue(row, "LNT")));
            wifi.setWorkDate(getTagValue(row, "WORK_DTTM"));

            wifiList.add(wifi);
        }

        return wifiList;
    }

    private static String getTagValue(Element element, String tagName) {
        Node node = element.getElementsByTagName(tagName).item(0);
        return (node != null) ? node.getTextContent() : null;
    }

    private static int parseInt(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double parseDouble(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Double.parseDouble(value) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
