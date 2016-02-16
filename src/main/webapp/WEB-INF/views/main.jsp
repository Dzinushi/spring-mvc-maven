<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Garden</h2>
<table border="1" cellpadding="3" cellspacing="1">
    <tr>
        <th>Виды</th>
        <th>Описание</th>
    </tr>
    <c:forEach items="${trees}" var="tree">
        <tr>
            <form:form method="post" action="details">
                    <input name="id" type="hidden" value="${tree.id}">
                <td><input type="submit" value="${tree.name}"></td>
                <td>${tree.care.describe}</td>
            </form:form>
        </tr>
    </c:forEach>
</table>
<h2></h2>
<form:form method="post" action="add">
    <input type="submit" value="Добавить">
</form:form>
</body>
</html>