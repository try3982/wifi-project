<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>위치 히스토리 목록</title>
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
            width: 80%;
            margin: 20px auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
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

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<nav>
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load_wifi.jsp">Open API 와이파이 정보 가져오기</a>
</nav>
<div class="container">
    <h1>위치 히스토리 목록</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>위도</th>
            <th>경도</th>
            <th>조회 시간</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="history" items="${historyList}">
            <tr>
                <td>${history.id}</td>
                <td>${history.latitude}</td>
                <td>${history.longitude}</td>
                <td>${history.queryTime}</td>
                <td>
                    <form action="delete-history" method="post">
                        <input type="hidden" name="id" value="${history.id}">
                        <button type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${historyList == null || historyList.size() == 0}">
            <tr>
                <td colspan="5" clas
                    s="no-data">저장된 히스토리가 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
