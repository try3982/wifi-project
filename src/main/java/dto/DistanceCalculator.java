package dto;

public class DistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0;

    // 두 지점 간 거리 계산 메서드
    public static double calculateDistance(double userLat, double userLng, double wifiLat, double wifiLng) {
        double latDistance = Math.toRadians(userLat - wifiLat);
        double lngDistance = Math.toRadians(userLng - wifiLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(wifiLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
