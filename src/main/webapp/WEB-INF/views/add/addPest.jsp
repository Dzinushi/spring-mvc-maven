<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Новый вредитель</h2>
</head>
<body>
<h3>Название</h3>
<form:form method="post" action="../add/newPest" onsubmit="return validateForm()">
    <input type="text" id="name_id" name="name"> <span style='color:red' id='nameV_id'></span>
    <br>
    <br>
    <input type="submit" value="Подтвердить">
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