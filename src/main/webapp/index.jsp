<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="bank" method="post">
     <div class="buttonFirst">
        <input class="buttonFirst" type="text" name="userid" value="${userid}">
        <button class="buttonFirst">.get</button>
        <input type="hidden" name="flag" value="user">
    </div>
</form>
    <form action="bank" method="post">
    <div class="buttonSecond">
        <input class="buttonSecond" type="text" name="account" value="${account}">
        <button class="buttonSecond">.get</button>
        <input type="hidden" name="flag" value="account">
    </div>
</form>
</body>
</html>
