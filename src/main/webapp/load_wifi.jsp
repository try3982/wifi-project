<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Open API Wi-Fi 정보 가져오기</title>
</head>
<body>
<h1>Open API Wi-Fi 정보 가져오기</h1>
<c:choose>
    <c:when test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:when>
    <c:when test="${not empty savedCount}">
        <p class="success">${savedCount}개의 Wi-Fi 정보를 성공적으로 저장했습니다.</p>
    </c:when>
    <c:otherwise>
        <p>Wi-Fi 정보를 가져오는 중입니다. 잠시만 기다려주세요.</p>
    </c:otherwise>
</c:choose>
<a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>
