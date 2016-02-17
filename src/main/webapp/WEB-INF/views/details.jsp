<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>${treeDTO.trees.get(0).name}</h1>
    <table border="1" cellspacing="1" cellpadding="3">
        <tr>
            <td>Семейство</td>
            <td>Высота</td>
            <td>Описание</td>
            <td>Отрава для вредителей</td>
            <td>Вредители</td>
        </tr>
        <c:forEach items="${treeDTO.trees}" var="tree">
            <tr>
                <td>${tree.care.describe}</td>
                <td>${tree.height}</td>
                <td>${tree.describe}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>