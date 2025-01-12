<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>WiFi 정보</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9; }
    nav { background-color: #007bff; padding: 10px; color: white; text-align: center; }
    nav a { color: white; margin: 0 15px; text-decoration: none; font-weight: bold; }
    nav a:hover { text-decoration: underline; }
    .container { width: 90%; margin: 20px auto; padding: 20px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
    th { background-color: #28a745; color: white; }
    .no-data { text-align: center; color: gray; font-style: italic; }
  </style>
</head>
<body>
<nav>
  <a href="index.jsp">홈</a> | <a href="history.jsp">히스토리 목록</a> | <a href="load_wifi.jsp">와이파이 가져오기</a>
</nav>
<div class="container">
  <h1>WiFi 정보</h1>
  <table>
    <thead>
    <tr>
      <th>거리 (Km)</th>
      <th>관리번호</th>
      <th>자치구</th>
      <th>와이파이명</th>
      <th>도로명주소</th>
      <th>상세주소</th>
      <th>X좌표</th>
      <th>Y좌표</th>
      <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="wifi" items="${wifiList}">
      <tr>
        <td>${wifi.distance}</td>
        <td>${wifi.manageNum}</td>
        <td>${wifi.district}</td>
        <td>${wifi.name}</td>
        <td>${wifi.roadAddress}</td>
        <td>${wifi.detailAddress}</td>
        <td>${wifi.latitude}</td>
        <td>${wifi.longitude}</td>
        <td>${wifi.workDate}</td>
      </tr>
    </c:forEach>
    <c:if test="${wifiList == null || wifiList.size() == 0}">
      <tr>
        <td colspan="9" class="no-data">근처 WiFi 정보를 불러오지 못했습니다.</td>
      </tr>
    </c:if>
    </tbody>
  </table>
</div>
</body>
</html>
