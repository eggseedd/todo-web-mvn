<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <div class="container">
        <header class="center-text"> 
            <h1>Edit Task</h1>
        </header>
        <form action="todo" method="post">
            <input type="hidden" name="id" value="${todo.id}">
            <label>Task:</label>
            <input type="text" name="task" value="${todo.task}" required>
            <label>Deadline:</label>
            <input type="date" name="deadline" value="${todo.deadline}" required>
            <button type="submit" name="action" value="update" style="margin-top: 10px;" >Update</button>
            <button type="button" onclick="location.href='dashboard.jsp'" class="grey-button">Cancel</button>
        </form>
    </div>
</body>
</html>
