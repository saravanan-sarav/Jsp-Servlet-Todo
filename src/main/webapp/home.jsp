<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Todo List</title>
</head>
<body>

<h2>Todo List </h2>

<form action="logout.jsp" method="POST">
<input type="submit" value="Logout"/>
</form>

<form action="todo" method="POST">
<p>Enter Item: <input type="text"name="username"/></p>
<input type="submit" value="Add Item">
</form>

<c:if test="${todoList.size() eq 0}">
<p>No Item in List...</p>
<p>Keep it Up..!!!</p>
</c:if>

<c:if test ="${todoList.size() gt 0}"> <html
<table:option>
    <thead>
        <th>Id</th>
        <th> Item </th>
        <th>Action</th>
    </thead>
    <tbody>
    <c:forEach var="todo" items="${todoList}">
        <tr>
            <td><c:out value="${todo.id}" /></td>
            <td><c:out value="${todo.item}" /></td>
            <td><a href="todo?id=<c:out value='${todo.id}' />">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table:option>
</c:if>
</body>
</html>