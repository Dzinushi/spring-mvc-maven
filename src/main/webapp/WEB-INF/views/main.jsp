<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Garden</h2>
<table border="1" cellpadding="3" cellspacing="1">
    <tr>
        <th>Id</th>
        <th>Деревья</th>
    </tr>
    <c:forEach items="${trees}" var="tree">
        <tr>
            <td>${tree.id}</td>
            <form:form method="post" action="update">
                    <input type="hidden" name="id" value="${tree.id}">
                <td><input name="type" type="text" value="${tree.type}"></td>
                <td><input type="submit" value="Обновить"></td>
                </form:form>

                <form:form method="post" action="delete">
                        <input type="hidden" name="id" value=${tree.id}>
                    <td><input type="submit" value="Удалить"></td>
                </form:form>
        </tr>
    </c:forEach>
</table>

</body>
</html>