<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<form>
  LAT: <input type="text" name="lat" value="37.5544069">
  LNT: <input type="text" name="lnt" value="126.8998666">
  <button type="submit">근처 WiFi 정보 보기</button>
</form>
<table>
  <tr>
    <th>거리 (Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치 (층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외 구분</th>
    <th>WiFi 접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>
  <c:forEach var="wifi" items="${wifiList}">
    <tr>
      <td>${wifi.distance}</td>
      <td>${wifi.manageNum}</td>
      <td>${wifi.district}</td>
      <td>${wifi.name}</td>
      <td>${wifi.roadAddress}</td>
      <td>${wifi.detailAddress}</td>
      <td>${wifi.floor}</td>
      <td>${wifi.type}</td>
      <td>${wifi.agency}</td>
      <td>${wifi.serviceType}</td>
      <td>${wifi.connection}</td>
      <td>${wifi.installYear}</td>
      <td>${wifi.indoor}</td>
      <td>${wifi.wifiEnv}</td>
      <td>${wifi.latitude}</td>
      <td>${wifi.longitude}</td>
      <td>${wifi.workDate}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
