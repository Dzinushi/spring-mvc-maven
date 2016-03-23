<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Новый яд</h2>
</head>
<body>

<form:form method="post" action="../add/newPoison" onsubmit="return validateForm()">
    <h3>Название</h3>
    <input type="text" id="name_id" name="name" value="">  <span style='color:red' id='nameV_id'></span>
    <br>
    <c:if test="${pests.size() > 0}">
        <h3>Вредители</h3>
        <table border="1" cellpadding="3" cellspacing="1">
            <c:forEach items="${pests}" var="pest">
                <tr>
                    <input type="hidden" name="idPests" value="${pest.id}">
                    <td>${pest.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>
    <input type="submit" value="Подтвердить">
</form:form>

<form:form method="get" action="../details/detailsPests">
    <input type="submit" value="Выбрать вредителей">
</form:form>

<script type="text/javascript">
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
</script>

</body>
</html>
