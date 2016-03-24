<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Изменение вредителя "${pest.name}"</head>
<body>
<form:form method="post" action="../applyUpdate" onsubmit="return validateForm()">
    <input type="hidden" name="id" value="${pest.id}">
    <h3>Имя</h3>
    <input type="text" id="name_id" name="name" value="${pest.name}"> <span style='color:red' id='nameV_id'></span>
    <br>
    <br>
    <input type="submit" value="Подтвердить изменения">
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
