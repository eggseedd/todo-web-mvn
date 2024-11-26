<%@ page import="com.eggseedproj.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <div class="container">
        <header class="center-text">
            <h2>Update Profile</h2>
        </header>
        <form action="updateProfile" method="post" class="profile-form">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${user.name}" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${user.password}" required>
            
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            
            <div style="margin-top: 10px;">
                <button type="submit">Save Changes</button>
                <button type="button" onclick="location.href='dashboard.jsp'" class="grey-button">Cancel</button>
            </div>
        </form>

        <form action="deleteAccount" method="post">
            <button type="submit" class="danger-button" name="action" value="deleteAccount">Delete Account</button>
        </form>
    </div>
</body>
</html>
