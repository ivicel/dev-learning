<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring Pizza</title>
</head>
<body>
    <h2>Welcome to Spring Pizza!!!</h2>
    <sf:form>
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
        <label for="phoneNumber">Phone Number</label>
        <input type="text" name="phoneNumber"/>
        <input type="submit" name="_eventId_phoneEnter" value="Lookup Customer"/>
    </sf:form>
</body>
</html>
