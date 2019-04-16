<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/css/blog.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="/static/bootstrap.min.js"></script>
    <script src="/static/js/bootstrap.bundle.min.js"></script>
    <title>${pageTitle}</title>

    <style type="text/css">
        body {
            padding-top: 10px;
            padding-bottom: 40px;
        }
    </style>
</head>
<body>
<div class="container">

<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

    <div class="row">
        <div class="col-md-9">
            <jsp:include page="${mainPage}"/>
        </div>
        <div class="col-md-3">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="/static/images/user_icon.png" alt="user icon">博主信息
                </div>
                <div class="user_image">
                    <img src="/static/images/userImages/${blogger.imageName}" alt="blogger image">
                </div>
                <div class="nickName">${blogger.nickName}</div>
                <div class="userSign">(${blogger.sign})</div>
            </div>
            <div class="data_list">
                <div class="data_list_title">
                    <img src="/static/images/byType_icon.png" alt="">按日志类别
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogTypeCount" items="${blogTypeCountList}">
                            <li>
                                <span><a href="${pageContext.request.contextPath}?typeId=${blogTypeCount.id}">
                                    ${blogTypeCount.typeName}(${blogTypeCount.blogCount})
                                </a></span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="data_list">
                <div class="data_list_title">
                    <img src="/static/images/byDate_icon.png" alt="">按日志日期
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogCount" items="${blogCountList}">
                            <li>
                                <span><a href="${pageContext.request.contextPath}?releaseDateStr=${blogCount.releaseDateStr}">
                                    ${blogCount.releaseDateStr}(${blogCount.blogCount})
                                </a></span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="data_list">
                <div class="data_list_title">
                    <img src="/static/images/link_icon.png" alt="">友情链接
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="link" items="${linkList}">
                            <li>
                                <span><a href="${link.linkName}" target="_blank">
                                    ${link.linkName}
                                </a></span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
