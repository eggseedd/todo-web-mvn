<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <div class="container">
    <header class="center-text">
        <h1>Register</h1>
    </header>

    <form action="auth" method="post" class="flex-column">
        <input type="hidden" name="action" value="register">
        <label>Name:</label>
        <input type="text" name="name" required>
        <label>Email:</label>
        <input type="email" name="email" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit" style="margin-top: 10px;">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
    </div>
</body>
</html>
