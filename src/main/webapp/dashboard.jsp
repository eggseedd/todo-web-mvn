<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <div class="container">
        <!-- Header -->
        <header class="center-text">
            <h1>Welcome, ${sessionScope.user.name}</h1>
            <a href="profile.jsp">Go to Profile</a>
        </header>

        <!-- To-Do List Section -->
        <h2 class="center-text">Your To-Do List</h2>

        <!-- Add Task Form -->
        <form action="todo" method="post" class="flex-column">
            <label for="task">New Task:</label>
            <input type="text" id="task" name="task" required>
            
            <label for="deadline">Deadline:</label>
            <input type="date" id="deadline" name="deadline" required>
            
            <button type="submit">Add Task</button>
        </form>

        <!-- Filter and Sort Section -->
        <div class="flex-row-div filter-sort">
            <!-- Filter Form -->
            <form action="todo" method="get" class="flex-center">
                <div class="flex-row-div" style="margin-bottom: 10px;">
                    <label for="startDate">Filter by Deadline:</label>
                    <input type="date" id="startDate" name="startDate" value="${param.startDate}">
                    
                    <label for="endDate">to</label>
                    <input type="date" id="endDate" name="endDate" value="${param.endDate}">
                </div>
                
                <div class="flex-row-div">
                    <button type="submit" name="action" value="filter">Filter</button>
                    <button type="submit" name="action" value="clear">Clear Filter</button>
                </div>
            </form>

            <!-- Sort Form -->
            <form action="todo" method="get" class="flex-center">
                <button type="submit" name="sort" value="asc">Sort by Deadline (Ascending)</button>
                <button type="submit" name="sort" value="desc">Sort by Deadline (Descending)</button>
            </form>
        </div>

        <!-- To-Do List -->
        <ul>
            <c:forEach var="todo" items="${sessionScope.todos}">
                <li class="flex-row-div todo-item">
                    <span>${todo.task} - ${todo.deadline}</span>
                    <div class="flex-row-div">
                        <form action="todo" method="get" style="display:inline">
                            <input type="hidden" name="id" value="${todo.id}">
                            <input type="hidden" name="task" value="${todo.task}">
                            <input type="hidden" name="deadline" value="${todo.deadline}">
                            <button type="submit" name="action" value="edit" class="edit-button">Edit</button>
                        </form>
                        <form action="todo" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${todo.id}">
                            <button type="submit" name="action" value="delete" class="danger-button">Delete</button>
                        </form>
                    </div>
                </li>
            </c:forEach>
        </ul>

        <!-- Logout Button -->
        <form action="auth" method="post" class="flex-center">
            <button type="submit" name="action" value="logout" class="grey-button">Logout</button>
        </form>
    </div>
</body>
</html>
