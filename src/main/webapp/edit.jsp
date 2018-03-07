<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>${meal.description}</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${meal.uuid}">
        <dl>
            <dt>Описание:</dt>
            <dd><input type="text" name="description" size=50 value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Дата:</dt>
            <dd><input type="datetime-local" name="dateTime" size=50 value="${meal.dateTime}"></dd>
        </dl>
        <dl>
            <dt>Каллории:</dt>
            <dd><input type="number" name="calories" size=50 value="${meal.calories}"></dd>
        </dl>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>
