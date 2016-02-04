<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Hello World!</h2>
<table border="1" cellpadding="3" cellspacing="1">
    <tr>
        <th>Id</th>
        <th>Деревья</th>
    </tr>
    <c:forEach items="${trees}" var="tree">
        <tr>
            <td>${tree.id}</td>
            <td>${tree.type}</td>
            <td>
                <form:form method="post" action="delete">
                    <input type="hidden" name="id" value=${tree.id}>
                    <input type="submit" value="Удалить">
                </form:form>
            </td>
            <td>
                <form:form method="post" action="edit">
                    <input type="hidden" name="id" value=${tree.id}>
                    <input type="submit" value="Изменить">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>