package com.eggseedproj.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eggseedproj.models.ToDo;
import com.eggseedproj.models.User;
import com.eggseedproj.services.ToDoService;

//@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Get the full user object
        if (user != null) {
            List<ToDo> todos = toDoService.getToDosByUser(user.getId()); // Use user.getId()
            session.setAttribute("todos", todos);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect if user is not logged in
        }
    }
}
