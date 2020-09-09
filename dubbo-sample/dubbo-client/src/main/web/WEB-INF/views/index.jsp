<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
    <meta charset="utf-8">
    <title>hello, world</title>
  </head>
  <body>
      hello, ${name}
  </body>
</html>
