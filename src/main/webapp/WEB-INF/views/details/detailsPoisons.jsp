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
            <td><input type="checkbox" name="checkedPoisons" value="${poison.id}"></td>
            <form:form method="get" action="../update/updatePoison">
                <input type="hidden" name="id" value="${poison.id}">
                <td><input type="submit" value="Обновить"></td>
            </form:form>
            <form:form method="post" action="../delete/deletePoison">
                <input type="hidden" name="id" value="${poison.id}">
                <td><input type="submit" value="Удалить"></td>
            </form:form>
        </tr>
    </c:forEach>
</table>

<form:form method="post" action="../add/addPoison">
    <br>
    <input type="submit" value="Добавить">
</form:form>

<form:form method="post" action="../details/applyPoisons" onsubmit="return checked()">
    <input type="hidden" id="poisons_id" name="poisons" value="">
    <input type="submit" value="Подтвердить">
</form:form>

<script type="text/javascript">
    function checked() {
        var checkBox = document.getElementsByName('checkedPoisons');

        var checked = [];
        for(var i=0; i < checkBox.length; ++i){
            if(checkBox[i].checked){
                checked.push(checkBox[i].value);
            }
        }
        document.getElementById('poisons_id').value = checked;
    }
</script>

</body>
</html>