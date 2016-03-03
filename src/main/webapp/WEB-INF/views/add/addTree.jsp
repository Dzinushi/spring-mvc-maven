<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <h2>Новое дерево</h2>
</head>
<body onload="createTable()">

<form:form method="post" action="../add/applyTree">
    <h3>Название</h3>
    <input type="text" name="name">
    <h3>Высота</h3>
    <input type="text" name="height">
    <h3>Описание</h3>
    <input type="text" name="describe">
    <h3>Уход</h3>
    <input type="text" name="care">
    <h3>Отрава для вредителей</h3>

    <table border="1" cellspacing="3" cellpadding="1">
        <tr>
            <th>Название отравы</th>
            <th>Вредители</th>
        </tr>
        <c:forEach items="${poisons}" var="poison">
            <tr>
                <input type="hidden" name="idPoisons" value="${poison.id}">
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
    <input type="submit" value="Подтвердить">
</form:form>

<form:form method="get" action="../details/detailsPoison">
    <input type="submit" value="Выбрать отраву">
</form:form>

<%--<script type="text/javascript">--%>
    <%--function createTable() {--%>
        <%--var myTable = document.getElementById("poisonTable");--%>
        <%--var table = document.createElement("table");--%>
        <%--var tableBody = document.createElement("tbody");--%>

        <%--// Формирование шапки таблицы--%>
        <%--for (var j = 0; j < 2; ++j){--%>
            <%--var row = document.createElement("tr");--%>
            <%--var cellTh = document.createElement("th");--%>
            <%--var cellText = document.createTextNode("Название отравы");--%>
            <%--cellTh.appendChild(cellText);--%>
            <%--row.appendChild(cellTh);--%>
            <%--cellTh = document.createElement("th");--%>
            <%--cellText = document.createTextNode("Вредители");--%>
            <%--cellTh.appendChild(cellText);--%>
            <%--row.appendChild(cellTh);--%>
            <%--tableBody.appendChild(row);--%>
            <%--break;--%>
        <%--}--%>

        <%--table.appendChild(tableBody);--%>
        <%--table.setAttribute("border", "1");--%>
        <%--myTable.appendChild(table);--%>
    <%--}--%>
<%--</script>--%>

</body>
</html>