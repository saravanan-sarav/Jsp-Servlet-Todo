<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<body>
<h2>Login</h2>
<form action="login" method="POST">
<p>Username: <input type="text"name="username"/></p>
<p>Password: <input type="text"name="password"/></p>
<%
if(request.getAttribute("error")!=null){
    out.print("Invalid Credentials..!!");
}
%>
<input type="submit" value="login">
</form>
</body>
</html>