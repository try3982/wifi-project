package dto;

import java.time.LocalDateTime;

public class WifiInfo {
    private Double distance; //거리
    private String  manageNum; //관리번호
    private String  district; // 자치구
    private String name; // 와이파이 명
    private String roadAddress; // 도로명 주소
    private String detailAddress; //상세 주소
    private String  floor; // 설치 위치 (층)
    private String type ;// 설치 유형
    private String agency; // 설치 기관
    private String serviceType; // 서비스 구분;
    private String connection; //망 종류
    private int installYear; //설치 년도;
    private String indoor; // 실내외 구분
    private String wifiEnv; // 와이파이 접속환경
    private double latitude; // 위도(X좌표)
    private double longitude; // 경도(Y좌표)
    private LocalDateTime TiemSamp;;

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getWifiEnv() {
        return wifiEnv;
    }

    public void setWifiEnv(String wifiEnv) {
        this.wifiEnv = wifiEnv;
    }

    public String getIndoor() {
        return indoor;
    }


    public void setIndoor(String indoor) {
        this.indoor = indoor;
    }

    public int getInstallYear() {
        return installYear;
    }

    public void setInstallYear(int installYear) {
        this.installYear = installYear;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getManageNum() {
        return manageNum;
    }

    public void setManageNum(String manageNum) {
        this.manageNum = manageNum;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private String workDate; //작업일자

}
