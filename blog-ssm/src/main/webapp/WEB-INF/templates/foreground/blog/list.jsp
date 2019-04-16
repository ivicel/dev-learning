<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/list_icon.png" alt="title image">
    </div>
    <div class="datas">
        <ul>
            <c:forEach items="${blogList}" var="blog">
                <li style="margin-bottom: 30px;">
                    <a class="date" href="${pageContext.request.contextPath}/blog/articles/${blog.id}">
                        <fmt:formatDate value="${blog.releaseDate}" pattern="yyyy-MM-dd" type="date"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}" class="title">${blog.title}</a>
                    <span class="summary">摘要: ${blog.summary}...</span>
                    <span class="img">
                        <c:forEach var="image" items="${blog.imageList}">
                            <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}">${image}&nbsp;&nbsp;</a>
                        </c:forEach>
                    </span>
                    <span class="info">
                        发表于 <fmt:formatDate value="${blog.releaseDate}" pattern="yyyy-MM-dd" type="date"/> 阅读(${blog.clickHit}) 评论(${blog.replyHit})
                    </span>
                </li>
                <hr style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;">
            </c:forEach>
        </ul>
    </div>
</div>

<div>
    <nav>
        <ul class="pagination pagination-sm">${pageCode}</ul>
    </nav>
</div>
