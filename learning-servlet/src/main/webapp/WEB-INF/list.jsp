<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>No.</th>
        <th>EmpNo.</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Birth date</th>
        <th>Hire date</th>
    </tr>
    </thead>
    <tbody>
    ${requestScope.result}
    <c:forEach items="${requestScope.result.t}" var="em" varStatus="s">
        <tr style='background-color: ${s.count % 2 == 0 ? "gray" : ""}'>
            <td>${s.count}</td>
            <td>${em.empNo}</td>
            <td>${em.firstName}</td>
            <td>${em.lastName}</td>
            <td>${em.birthDate}</td>
            <td>${em.hireDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
