<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Описание</th>
            <th>Время</th>
            <th>Каллории</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="formatter" scope="request" type="java.time.format.DateTimeFormatter"/>
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr <c:if test="${meal.exceed eq true}">bgcolor="#FF9000"</c:if>>
                <td><a href="meal?uuid=${meal.uuid}&action=edit">${meal.description}</a></td>
                <td>${meal.dateTime.format(formatter)}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>