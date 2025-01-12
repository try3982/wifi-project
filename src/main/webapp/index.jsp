<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        nav {
            background-color: #007bff;
            padding: 10px;
            color: white;
            text-align: center;
        }

        nav a {
            color: white;
            margin: 0 15px;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        .container {
            width: 90%;
            margin: 20px auto;
            padding: 20px;
        }

        header {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 14px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 6px;
            text-align: center;
        }

        th {
            background-color: #28a745;
            color: white;
        }

        .no-data {
            text-align: center;
            color: gray;
            font-style: italic;
        }

        .input-group {
            margin-bottom: 20px;
            text-align: center;
        }

        .input-group label {
            margin-right: 10px;
        }

        .input-group input {
            margin-right: 10px;
            padding: 5px;
            font-size: 14px;
            width: 150px;
        }

        .button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    function (position) {
                        const latitude = position.coords.latitude;
                        const longitude = position.coords.longitude;
                        document.getElementById("lat").value = latitude.toFixed(6);
                        document.getElementById("lnt").value = longitude.toFixed(6);
                    },
                    function (error) {
                        switch (error.code) {
                            case error.PERMISSION_DENIED:
                                alert("위치 정보를 허용하지 않았습니다.");
                                break;
                            case error.POSITION_UNAVAILABLE:
                                alert("위치 정보를 사용할 수 없습니다.");
                                break;
                            case error.TIMEOUT:
                                alert("위치 요청 시간이 초과되었습니다.");
                                break;
                            default:
                                alert("알 수 없는 오류가 발생했습니다.");
                        }
                    }
                );
            } else {
                alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
            }
        }
    </script>
</head>
<body>
<nav>
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load_wifi.jsp">Open API 와이파이 정보 가져오기</a>
</nav>
<div class="container">
    <header>
        <h1>와이파이 정보 구하기</h1>
    </header>

    <div class="input-group">
        <form action="wifi-servlet" method="get">
            <label for="lat">LAT:</label>
            <input type="text" id="lat" name="lat" value="0.0">

            <label for="lnt">LNT:</label>
            <input type="text" id="lnt" name="lnt" value="0.0">

            <button type="button" class="button" onclick="getLocation()">내 위치 가져오기</button>
            <button type="submit" class="button">근처 WIFI 정보 보기</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
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
        <c:if test="${wifiList == null || wifiList.size() == 0}">
            <tr>
                <td colspan="17" class="no-data">위치 정보를 입력한 후에 조회해 주세요.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
