<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>${tree.name}</h1>
<h3>Высота</h3>
${tree.height}
<h3>Описание</h3>
${tree.describe}
<h3>Уход</h3>
${tree.careDTO.describe}
<h3>Отрава для вредителей</h3>
<table border="1" cellspacing="1" cellpadding="3">
    <th align="center">Вид</th>
    <th align="center">Название</th>
    <th align="center">Вредители</th>
    <c:forEach items="${tree.poisonDTOs}" var="poison">
    <tr>
        <td>${poison.type}</td>
        <td>${poison.name}</td>
        <td>
            <c:forEach items="${poison.pestDTOs}" var="pest">
                ${pest.name}
                <br>
            </c:forEach>
        </td>
    </tr>
    </c:forEach>
</table>
<br>
<form method="post" action="details/updateTree">
    <input type="submit" value="Изменить">
</form>
</body>
</html>