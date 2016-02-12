<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<%--<h2>Garden</h2>--%>
<%--<table border="1" cellpadding="3" cellspacing="1">--%>
    <%--<tr>--%>
        <%--<th>Id</th>--%>
        <%--<th>Деревья</th>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${treeTypes}" var="treeType">--%>
        <%--<tr>--%>
            <%--<td>${treeType.id}</td>--%>
            <%--<form:form method="post" action="update">--%>
                    <%--<input type="hidden" name="id" value="${treeType.id}">--%>
                <%--<td><input name="type" type="text" value="${treeType.type}"></td>--%>
                <%--<td><input type="submit" value="Обновить"></td>--%>
                <%--</form:form>--%>

                <%--<form:form method="post" action="delete">--%>
                        <%--<input type="hidden" name="id" value=${treeType.id}>--%>
                    <%--<td><input type="submit" value="Удалить"></td>--%>
                <%--</form:form>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
<%--<h2></h2>--%>
<%--<form:form method="post" action="add">--%>
    <%--<input type="submit" value="Добавить">--%>
<%--</form:form>--%>
</body>
</html>