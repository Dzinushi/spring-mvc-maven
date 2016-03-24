<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>Изменение яда "${poison.name}"</head>
<body onload="createTable()">
<form:form method="post" action="../update/updatePoison/applyUpdate" onsubmit="return validateForm()">
    <input type="hidden" name="id" value="${poison.id}">
    <h3>Имя</h3>
    <input type="text" id="name_id" name="name" value="${poison.name}"> <span style='color:red' id='nameV_id'></span>
    <h3>Эффективен против вредителей</h3>
    <table id="pestTable" border="1" cellspacing="1" cellpadding="3">
    </table>
    <br>
    <input type="button" value="Добавить связь" onclick="addLink()">
    <br>
    <br>
    <input type="submit" value="Подтвердить изменения">
</form:form>

<script type="text/javascript">

    var trN;
    var poison = [];
    poison.id = "${poison.id}";
    poison.name = "${poison.name}";
    poison.pests = new Array();
    <c:forEach items="${poison.pestDTOs}" var="pestDTO">
        var pestDTO = new Object();
        pestDTO.name = '${pestDTO.name}';
        poison.pests.push(pestDTO);
    </c:forEach>

    var allPests = new Array();
    <c:forEach items="${allPests}" var="pest">
        var pest = new Object();
        pest.name = '${pest.name}';
        allPests.push(pest);
    </c:forEach>

    function validateForm() {
        var name = document.getElementById("name" + "_id").value;
        var regExp = new RegExp("^[А-Яа-я ]{2,15}$");
        if (name.length == 0){
            document.getElementById("name" + "V_id").innerHTML = '(*) обязательное поле';
            return false;
        }
        else if (regExp.test(name) == false){
            document.getElementById("name" + "V_id").innerHTML = '(*) поле должно содержать только буквы русского алфавита в количестве от 2 до 15';
            return false;
        }
        else {
            document.getElementById("name" + "V_id").innerHTML = '';
        }
        return true;
    }
    function createTable() {
        var table = document.getElementById("pestTable");
        trN = ${poison.pestDTOs.size()};

        for (var j = 0; j < poison.pests.length; ++j){
            var row = table.insertRow();
            row.id = j;

            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);

            var select = document.createElement("select");
            select.name = "allPests";
            for (var i = 0; i < allPests.length; ++i){
                var option = document.createElement("option");
                option.value = allPests[i].name;
                option.selected = poison.pests[j].name.localeCompare(allPests[i].name) == 0 ? 'selected = selected' : '';
                option.text = allPests[i].name;
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
        var table = document.getElementById("pestPoisonTable");
        var row = table.insertRow();
        row.id = trN;

        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);

        var select = document.createElement("select");
        select.name = "allPests";
        for (var i = 0; i < allPests.length; ++i){
            var option = document.createElement("option");
            option.value = allPests[i].name;
            option.text = allPests[i].name;
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
