<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Spittr</title>
    <link rel="stylesheet" href="<c:url value="/resources/style.css"/>">
</head>
<body>
    <h1>Welcome to Spittr</h1>
    <p>${spittle.message}</p>
    <p>${spittle.time}</p>
</body>
</html>
