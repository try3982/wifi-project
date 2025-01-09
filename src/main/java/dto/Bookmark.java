package dto;

import java.time.LocalDateTime;

public class Bookmark {

    private int bookmarkId; // 북마크 아dlel;
    private String GroupName; // 북마크 이름
    private int ortOrder; //정렬 순서
    private LocalDateTime createdAt; //등록일자
    private LocalDateTime updatedAt; // 수정일자
    private String remarks; // 비고
    private int wifiId; //와이파이 아이디(외래키)
    private  int groupId; // 그룹아이디;
    private String  wifiName;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getOrtOrder() {
        return ortOrder;
    }

    public void setOrtOrder(int ortOrder) {
        this.ortOrder = ortOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getWifiId() {
        return wifiId;
    }

    public void setWifiId(int wifiId) {
        this.wifiId = wifiId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }




}
