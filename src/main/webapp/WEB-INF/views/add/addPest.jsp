<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Новый вредитель</h2>
</head>
<body>
<h3>Название</h3>
<form:form method="post" action="../details/detailsPests">
    <input type="text" name="name">
    <input type="submit" value="Подтвердить">
</form:form>
</body>
</html>