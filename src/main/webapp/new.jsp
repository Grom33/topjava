<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Добавить еду</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="">
        <dl>
            <dt>Описание:</dt>
            <dd><input type="text" name="description" size=50 value=""></dd>
        </dl>
        <dl>
            <dt>Дата:</dt>
            <dd><input type="datetime-local" name="dateTime" size=50 value=""></dd>
        </dl>
        <dl>
            <dt>Каллории:</dt>
            <dd><input type="number" name="calories" size=50 value=""></dd>
        </dl>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>