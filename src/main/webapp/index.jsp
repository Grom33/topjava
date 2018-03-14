<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="meals">Meals</a></li>
</ul>


<form method="post" action="users">
    <select name="user">
        <option ${user == 1 ? 'selected' : 'choose'} value="1">Vasya</option>
        <option ${user == 2 ? 'selected' : 'choose'} value="2">Petya</option>
        <option ${user == 3 ? 'selected' : 'choose'} value="3">Jenya</option>
    </select>
    <button type="submit">Login</button>
</form>

</body>
</html>
