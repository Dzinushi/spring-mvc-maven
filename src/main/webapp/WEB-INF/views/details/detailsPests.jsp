<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Виды вредителей</h2>
</head>
<body>

<form:form method="post" action="../details/applyPests">

    <table border="1" cellspacing="1" cellpadding="3">
        <c:forEach items="${pests}" var="pest">
            <tr>
                <td>${pest.name}</td>
                <form:form method="post" action="../delete/deletePest">
                    <input type="hidden" name="id" value="${pest.id}">
                    <td><input type="submit" value="Удалить"></td>
                </form:form>
                <td><input type="checkbox" name="checkedPests" value="${pest.id}"></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input type="submit" value="Подтвердить">
</form:form>

<form:form method="post" action="../add/addPest">
    <br>
    <input type="submit" value="Добавить">
</form:form>

</body>
</html>