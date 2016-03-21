<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Виды вредителей</h2>
</head>
<body>

    <table border="1" cellspacing="1" cellpadding="3">
        <c:forEach items="${pests}" var="pest">
            <tr>
                <td>${pest.name}</td>

                <td>
                    <label>
                        <input type="checkbox" name="checkedPests" value="${pest.id}">
                    </label>
                </td>

                <form:form method="post" action="../update/updatePest">
                    <input type="hidden" name="id" value="${pest.id}">
                    <input type="hidden" name="name" value="${pest.name}">
                    <td><input type="submit" value="Обновить"></td>
                </form:form>

                <form:form method="post" action="../delete/deletePest">
                    <input type="hidden" name="id" value="${pest.id}">
                    <td><input type="submit" value="Удалить"></td>
                </form:form>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form:form method="post" action="../details/applyPests" onsubmit="return checked()">
        <input type="hidden" id="pests_id" name="pests" value="">
        <input type="submit" value="Подтвердить">
    </form:form>

<form:form method="post" action="../add/addPest">
    <br>
    <input type="submit" value="Добавить">
</form:form>

<script type="text/javascript">
    function checked() {
        var checkBox = document.getElementsByName('checkedPests');

        var checked = [];
        for(var i=0; i < checkBox.length; ++i){
            if(checkBox[i].checked){
                checked.push(checkBox[i].value);
            }
        }
        document.getElementById('pests_id').value = JSON.stringify(checked);
    }
</script>

</body>
</html>