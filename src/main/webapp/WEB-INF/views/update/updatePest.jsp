<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Изменение вредителя "${pest.name}"</head>
<body onload="createTable()">
<form:form method="post" action="../applyUpdate">
    <input type="hidden" name="id" value="${pest.id}">
    <h3>Имя</h3>
    <input type="text" name="name" value="${pest.name}">
    <h3>Подвержен действию следующих ядов</h3>
    <table id="pestPoisonTable" border="1" cellspacing="1" cellpadding="3">
    </table>
    <br>
    <input type="button" value="Добавить связь" onclick="addLink()">
    <br>
    <br>
    <input type="submit" value="Подтвердить изменения">
</form:form>

<script type="text/javascript">
    var trN;
    function createTable() {
        var table = document.getElementById("pestPoisonTable");
        trN = ${pest.poisonDTOs.size()};

        for (var j = 0; j < ${pest.poisonDTOs.size()}; ++j){
            var row = table.insertRow();
            row.id = j;
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            cell0.innerHTML = "<select name='poisonNames'>" +
                    "<c:forEach items='${allPoisons}' var='poison'>" +
                    "<option value='${poison.name}' ${pest.poisonDTOs.get(j).name == poison.name ? 'selected = "selected"' : ''}>${poison.name}</option>" +
                    "</c:forEach>" +
                    "</select>";
            cell1.innerHTML = "<input type='button' value='Удалить' onclick='deleteLink(" + j + ")'>";
            table.appendChild(row);
            break;
        }
    }
    function deleteLink(poisonId) {
        var row = document.getElementById(poisonId);
        row.parentNode.removeChild(row);
    }
    function addLink(){
        var table = document.getElementById("pestPoisonTable");
        var row = table.insertRow();
        row.id = trN;
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        cell0.innerHTML = "<select name='poisonNames'>" +
                            "<c:forEach items='${allPoisons}' var='poison'>" +
                                "<option value='${poison.name}'>${poison.name}</option>" +
                            "</c:forEach>" +
                            "</select>";
        cell1.innerHTML = "<input type='button' value='Удалить' onclick='deleteLink(" + trN + ")'>";
        table.appendChild(row);
        ++trN;
    }
</script>
</body>
</html>
