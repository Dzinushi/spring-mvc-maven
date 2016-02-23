<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Отрава для вредителей</h2>
</head>
<body>
<h4>Выберите необходимые элементы или добавьте новые</h4>
<table border="1" cellspacing="1" cellpadding="3">
    <tr>
        <th align="center">Виды ядов</th>
        <th align="center">Виды вредителей</th>
    </tr>

    <c:forEach items="${poisons}" var="poison">
        <tr>
            <td>${poison.name}</td>
                <td>
                    <c:forEach items="${poison.pestDTOs}" var="pest">
                        ${pest.name}
                        <br>
                    </c:forEach>
                </td>
            <td><input type="checkbox" id="${poison.id}"></td>
        </tr>
    </c:forEach>

</table>
<br>
<form:form method="post" action="../add/addPoison">
    <input type="submit" value="Добавить">
</form:form>
<form:form method="post" action="details/applyPoison">
    <input type="submit" value="Подтвердить">
</form:form>
</body>
</html>