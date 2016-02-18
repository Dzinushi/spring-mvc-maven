<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload="createTable()">

<h1>${tree.name}</h1>

<table id="treeDetailsTable">

</table>
    <%--<table border="1" cellspacing="1" cellpadding="3">--%>
        <%--<tr>--%>
            <%--<td rowspan="2" align="center">Название дерева</td>--%>
            <%--<td rowspan="2" align="center">Высота</td>--%>
            <%--<td rowspan="2" align="center">Описание</td>--%>
            <%--<td rowspan="2" align="center">Уход</td>--%>
            <%--<td colspan="2" align="center">Отрава для вредителей</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td align="center">Название</td>--%>
            <%--<td align="center">Вредители</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input type="text" name="name" value="${tree.name}"></td>--%>
            <%--<td><input type="text" name="height" value="${tree.height}"></td>--%>
            <%--<td><input type="text" name="describe" value="${tree.describe}"></td>--%>
            <%--<td><input type="text" name="care" value="${tree.care}"></td>--%>
            <%--<td>--%>
                <%--<c:forEach items="${tree.poisonDTOs}" var="poison">--%>
                    <%--${poison.type}--%>
                    <%--<br>--%>
                    <%--${poison.name}--%>
                    <%--<br>--%>
                <%--</c:forEach>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<c:forEach items="${tree.poisonDTOs}" var="poison">--%>
                    <%--<c:forEach items="${poison.pestDTOs}" var="pest">--%>
                        <%--${pest.name}--%>
                    <%--</c:forEach>--%>
                <%--</c:forEach>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>

<script type="text/javascript">
    function createTable() {
        var myTable = document.getElementById("treeDetailsTable");
        var table = document.createElement("table");
        var tableBody = document.createElement("tbody");

        var thFields = [
            "Название",
            "Высота",
            "Описание",
            "Уход",
            "Отрава для паразитов"
        ];

        // Формирование шапки таблицы
        for (var j = 0; j < 2; ++j){
            var row = document.createElement("tr");
            switch (j){

                case 0:
                    for (var i = 0; i < thFields.length; ++i){
                        var cellTh = document.createElement("th");
                        var cellText = document.createTextNode(thFields[i]);
                        cellTh.appendChild(cellText);
                        if (i != thFields.length - 1){
                            cellTh.rowSpan = 2;
                        }
                        else {
                            cellTh.colSpan = 2;
                        }
                        row.appendChild(cellTh);
                    }
                    tableBody.appendChild(row);
                    break;

                case 1:
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
        }

        <%--for (var i = 0; i < ${tree.poisonDTOs.size()}; ++i){--%>
            <%--var cellTd = document.createElement("td");--%>
            <%--var cellText = document.createTextNode();--%>
            <%--cellTd.appendChild(cellText);--%>
            <%--row.appendChild(cellTd);--%>
        <%--}--%>

        table.appendChild(tableBody);
        table.setAttribute("border", "1");
        myTable.appendChild(table);
    }
</script>
</body>
</html>