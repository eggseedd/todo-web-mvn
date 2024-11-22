package com.eggseedproj.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eggseedproj.models.User;
import com.eggseedproj.services.UserService;

//@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (userService.register(name, email, password)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=Registration failed.");
            }
        } else { // Login action
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userService.login(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Store the full user object
                session.setAttribute("userId", user.getId()); // Set userId for use in DashboardServlet
                response.sendRedirect("dashboard");
            } else {
                response.sendRedirect("login.jsp?error=Invalid credentials.");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate session on logout
        }
        response.sendRedirect("login.jsp");
    }
}

