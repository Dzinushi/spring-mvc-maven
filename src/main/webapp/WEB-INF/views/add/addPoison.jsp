<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Новый яд</h2>
</head>
<body>

<h3>Название</h3>
<input type="text" value="">
<br>
<br>
<form:form method="post" action="../details/applyPoisons">
    <input type="submit" value="Подтвердить">
</form:form>
<form:form method="get" action="../details/detailsPests">
    <input type="submit" value="Выбрать вредителей">
</form:form>
</table>
</body>
</html>
