<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h2>Новый яд</h2>
</head>
<body>

<form:form method="post" action="../details/detailsPoisons">
    <h3>Название</h3>
    <input type="text" name="name" value="">
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
</table>
</body>
</html>