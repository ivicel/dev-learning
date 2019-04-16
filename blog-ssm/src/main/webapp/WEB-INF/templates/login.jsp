<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Java个人博客系统后台登录页面</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function checkForm() {
            var username = $("#username").val();
            var password = $("#password").val();
            if (username == null || username === '') {
                $("#error").html("用户名不能为空");
                return false;
            }
            if (password == null || password === '') {
                $("#error").html("密码不能为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="top_div"></div>
    <span>${requestScope.get("errorInfo")}</span>
    <form action="${pageContext.request.contextPath}/blogger/login" method="post" onsubmit="return checkForm()">
        <p>
            <input type="text" id="username" name="username" class="ipt" placeholder="请输入用户名" value="${blogger.username}">
        </p>
        <p>
            <input type="password" id="password" class="ipt" name="password" placeholder="请输入密码" value="${blogger.password}">
        </p>
        <div>
            <a href="${pageContext.request.contextPath}">个人博客系统</a>
            <input type="submit" value="登录">
        </div>
    </form>
</body>
</html>
