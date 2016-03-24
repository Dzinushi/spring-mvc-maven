<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <h2>Новое дерево</h2>
</head>
<body onload="createTable()">

<form:form method="post" action="../" onsubmit="return validateForm()">
    <h3>Название</h3>
    <input type="text" id="name_id" name="name">            <span style='color:red' id='nameV_id'></span>
    <h3>Высота</h3>
    <input type="text" id="height_id" name="height">        <span style='color:red' id='heightV_id'></span>
    <h3>Описание</h3>
    <textarea id="describe_id" name="describe"></textarea>
    <h3>Уход</h3>
    <textarea id="care_id" name="care"></textarea>
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

<form:form method="get" action="../details/detailsPoisons">
    <input type="submit" value="Выбрать отраву">
</form:form>

<script type="text/javascript">
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
</script>

</body>
</html>