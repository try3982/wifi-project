<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open API 와이파이 정보 가져오기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
        }
        p.success {
            color: green;
        }
        p.error {
            color: red;
        }
        a {
            text-decoration: none;
            color: blue;
            font-size: 16px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Open API 와이파이 정보 가져오기</h1>

<c:choose>
    <c:when test="${not empty errorMessage}">
        <!-- 서버에서 전달된 에러 메시지가 있는 경우 -->
        <p class="error">${errorMessage}</p>
    </c:when>
    <c:when test="${not empty savedCount && savedCount > 0}">
        <!-- 저장된 WiFi 정보가 있는 경우 -->
        <p class="success">${savedCount}개의 WIFI 정보를 정상적으로 저장하였습니다.</p>
    </c:when>
    <c:otherwise>
        <!-- 저장된 데이터가 없고 에러 메시지도 없는 경우 -->
        <p class="error">WIFI 정보를 저장하지 못했습니다. 다시 시도해주세요.</p>
    </c:otherwise>
</c:choose>

<a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>
