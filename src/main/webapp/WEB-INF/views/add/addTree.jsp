<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <h2>Новое дерево</h2>
</head>
<body onload="createTable()">

<h3>Название</h3>
<input type="text">
<h3>Высота</h3>
<input type="text">
<h3>Описание</h3>
<input type="text">
<h3>Уход</h3>
<input type="text">
<h3>Отрава для вредителей</h3>
<table id="poisonTable">

</table>
<br>

<form:form method="get" action="../details/detailsPoison">
    <input type="submit" value="Выбрать отраву">
</form:form>

<script type="text/javascript">
    function createTable() {
        var myTable = document.getElementById("poisonTable");
        var table = document.createElement("table");
        var tableBody = document.createElement("tbody");

        // Формирование шапки таблицы
        for (var j = 0; j < 2; ++j){
            var row = document.createElement("tr");
            var cellTh = document.createElement("th");
            var cellText = document.createTextNode("Название отравы");
            cellTh.appendChild(cellText);
            row.appendChild(cellTh);
            cellTh = document.createElement("th");
            cellText = document.createTextNode("Вредители");
            cellTh.appendChild(cellText);
            row.appendChild(cellTh);
            tableBody.appendChild(row);
            break;
        }

    table.appendChild(tableBody);
    table.setAttribute("border", "1");
    myTable.appendChild(table);
    }
</script>

</body>
</html>