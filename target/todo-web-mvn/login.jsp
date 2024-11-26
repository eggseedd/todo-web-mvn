<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <div class="container">
        <header class="center-text">
            <h1>Login</h1>
        </header>

        <!-- Login Form -->
        <form action="auth" method="post" class="flex-column">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Login</button>
        </form>

        <!-- Centered Register Link -->
        <div class="center-text">
            <p style="color: black;">Don't have an account? <a href="register.jsp" style="color: #0056b3; text-decoration: none;">Register here</a>.</p>
        </div>
    </div>
</body>
</html>
