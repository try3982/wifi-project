package dto;

public class BookmarkGroup {

    private int groupId; // 북마크 그룹 Id
    private String groupName;  // 북마크 그룹이름

    public BookmarkGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public String toString() {
        return "BookmarkGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }

}
