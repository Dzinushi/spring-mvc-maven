<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>Изменение дерева "${tree.name}"</head>
<body onload="createTable()">
<form:form method="post" action="../update/updateTree/applyUpdate" onsubmit="return validateForm()">
    <input type="hidden" name="id" value="${tree.id}">
    <h3>Название</h3>
    <input type="text" id="name_id" name="name" value="${tree.name}">              <span style='color:red' id='nameV_id'></span>
    <h3>Высота</h3>
    <input type="text" id="height_id" name="height" value="${tree.height}">        <span style='color:red' id='heightV_id'></span>
    <h3>Описание</h3>
    <textarea id="describe_id" name="describe">${tree.describe}</textarea>
    <h3>Уход</h3>
    <textarea id="care_id" name="care">${tree.careDTO.describe}</textarea>
    <input type="hidden" name="care_id" value="${tree.careDTO.id}">
    <h3>Отрава для вредителей</h3>
    <table id="poisonPestTable" border="1" cellspacing="1" cellpadding="3">
    </table>
    <br>
    <input type="button" value="Добавить связь" onclick="addLink()">
    <br>
    <br>
    <input type="submit" value="Подтвердить изменения">
</form:form>

<script type="text/javascript">

    var trN;

    var tree = [];
    tree.id = "${tree.id}";
    tree.name = "${tree.name}";
    tree.describe = "${tree.describe}";
    tree.care = new Object();
    tree.care.describe = "${tree.careDTO.describe}";
    tree.poisons = new Array();
    <c:forEach items="${tree.poisonDTOs}" var="poisonDTO">
        var poison = new Object();
        poison.name = '${poisonDTO.name}';
        poison.pests = new Array();
        <c:forEach items="${poisonDTO.pestDTOs}" var="pestDTO">
            var pest = new Object();
            pest.name = '${pestDTO.name}';
            poison.pests.push(pest);
        </c:forEach>
        tree.poisons.push(poison);
    </c:forEach>

    var allPoisons = new Array();
    <c:forEach items="${allPoisons}" var="poisonDTO">
        var poison = new Object();
        poison.name = '${poisonDTO.name}';
        allPoisons.push(poison);
    </c:forEach>

    function validateForm() {
        var name = document.getElementById("name" + "_id").value;
        var regExpName = new RegExp("^[А-Яа-я ]{2,15}$");
        var result = true;

        if (name.length == 0){
            document.getElementById("name" + "V_id").innerHTML = '(*) обязательное поле';
            result = false;
        }
        else if (regExpName.test(name) == false){
            document.getElementById("name" + "V_id").innerHTML = '(*) поле должно содержать только буквы русского алфавита в количестве от 2 до 15';
            result = false;
        }
        else {
            document.getElementById("name" + "V_id").innerHTML = '';
        }

        var height = document.getElementById("height" + "_id").value;
        var regExpHeight = new RegExp("[0-9]{1,3}[А-Яа-я ]{0,2}-[0-9]{1,3}[А-Яа-я ]{1,2}");

        if (height.length == 0){
            document.getElementById("height" + "V_id").innerHTML = '(*) обязательное поле';
            result = false;
        }
        else if (regExpHeight.test(height) == false){
            document.getElementById("height" + "V_id").innerHTML = '(*) данные должны быть введены в виде "15-20м" или "15м-20м"';
            result = false;
        }
        else {
            document.getElementById("height" + "V_id").innerHTML = '';
        }

        return result;
    }
    function createTable() {
        var table = document.getElementById("poisonPestTable");
        trN = tree.poisons.length;

        for (var j = 0; j < tree.poisons.length; ++j){
            var row = table.insertRow();
            row.id = j;

            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);

            var select = document.createElement("select");
            select.name = "poisons";
            for (var i = 0; i < allPoisons.length; ++i){
                var option = document.createElement("option");
                option.value = allPoisons[i].name;
                option.selected = tree.poisons[j].name.localeCompare(allPoisons[i].name) == 0 ? 'selected = selected' : '';
                option.text = allPoisons[i].name;
                select.appendChild(option);
            }
            cell0.appendChild(select);

            cell1.innerHTML = "<input type='button' value='Удалить' onclick='deleteLink(" + j + ")'>";
            table.appendChild(row);
        }
    }
    function deleteLink(poisonId) {
        var row = document.getElementById(poisonId);
        row.parentNode.removeChild(row);
    }
    function addLink(){
        var table = document.getElementById("poisonPestTable");
        var row = table.insertRow();
        row.id = trN;

        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);

        var select = document.createElement("select");
        select.name = "poisons";
        for (var i = 0; i < allPoisons.length; ++i){
            var option = document.createElement("option");
            option.value = allPoisons[i].name;
            option.text = allPoisons[i].name;
            select.appendChild(option);
        }
        cell0.appendChild(select);

        cell1.innerHTML = "<input type='button' value='Удалить' onclick='deleteLink(" + trN + ")'>";
        table.appendChild(row);
        ++trN;
    }
</script>
</body>
</html>
