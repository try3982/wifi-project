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
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            text-align: center;
            padding-top: 50px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #28a745;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-size: 16px;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 가져오기</h1>
<p>${savedCount}개의 WIFI 정보를 정상적으로 저장하였습니다.</p>
<a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>
