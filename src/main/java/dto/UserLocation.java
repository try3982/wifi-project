package dto;

public class UserLocation {
    private double latitude; // 위도
    private double longitude; // 경도

    // 기본 생성자
    public UserLocation() {}

    // 매개변수 생성자
    public UserLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter와 Setter
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // 사용자 위치 출력 메서드
    @Override
    public String toString() {
        return "UserLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
