package dto;

import java.time.LocalDateTime;

public class Bookmark {
    private int bookmarkId; // 북마크 아읻;
    private String bookmarkName; // 북마크 이름
    private int ortOrder; //정렬 순서
    private LocalDateTime createdAt; //등록일자
    private LocalDateTime updatedAt; // 수정일자
    private String remarks; // 비고
    private int wifiId; //와이파이 아이디(외래키)
    private  int groupId; // 그룹아이디;


}
