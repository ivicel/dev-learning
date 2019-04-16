<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="data_list">
    <div>
        <div class="blog_title">
            <h3>${blog.title}</h3>
        </div>
    </div>
    <div class="blog_info">
        发布时间: <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd"/>
        博客类别: ${blog.blogType.typeName}
        阅读(${blog.clickHit})
        评论(${blog.replyHit})
    </div>
    <div class="blog_content">${blog.content}</div>
    <div class="blog_keyWord">
        <span style="{font-weight: 600}">关键字:</span>
        <c:choose>
            <c:when test="${keyWords == null}">无</c:when>
            <c:otherwise>
                <c:forEach var="keyWord" items="${keyWords}">
                    &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/blog/q.html?q=${keyWord}">
                        ${keyWord}
                    </a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="blog_lastAndNextPage">${pageCode}</div>
</div>
