<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<c:import url="/WEB-INF/style/table.css"/>

<h2>Garden</h2>
<table border="1" cellpadding="3" >
    <tr>
        <th align="center">Виды</th>
    </tr>
    <c:forEach items="${trees}" var="tree">
        <tr>
            <form:form method="get" action="details/detailsTree">
                    <input name="id" type="hidden" value="${tree.id}">
                <td align="center"><input type="submit" value="${tree.name}"></td>
            </form:form>
            <form:form method="post" action="delete">
                <input name="id" type="hidden" value="${tree.id}">
                <td><input type="submit" value="Удалить"></td>
            </form:form>
        </tr>
    </c:forEach>
</table>
<h2></h2>
<form:form method="post" action="add/addTree">
    <input type="submit" value="Добавить">
</form:form>
</body>
</html>