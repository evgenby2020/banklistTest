<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 24.04.2019
  Time: 6:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="bank" method="post">
    <%--<input type="text" name="id" value="${id}">--%>
    <%--<input type="hidden" value="user">--%>
    <%--<input type="submit"  value=".get">--%>
    <div class="buttonFirst">
        <input class="buttonFirst" type="text" name="userid" value="${userid}">
        <button class="buttonFirst">.get</button>
        <input type="hidden" name="flag" value="user">
        <%--<input type="submit" value="Save"/>--%>
    </div>
    <div class="buttonSecond">
        <input class="buttonSecond" type="text" name="account" value="${account}">
        <button class="buttonSecond">.get</button>
        <input type="hidden" name="flag" value="account">
    </div>
</form>
</body>
</html>
