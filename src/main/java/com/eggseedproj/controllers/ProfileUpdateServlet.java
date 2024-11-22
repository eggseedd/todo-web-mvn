package com.eggseedproj.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eggseedproj.models.User;
import com.eggseedproj.services.UserService;

public class ProfileUpdateServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if passwords match if a new password is entered
        if (password != null && !password.isEmpty() && !password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
            return;
        }

        // Update user profile
        user.setName(name);
        user.setEmail(email);
        if (password != null && !password.isEmpty()) {
            user.setPassword(password); // Update password
        }

        // Update user in the database
        userService.updateUser(user);

        // Update the session with the updated user object
        session.setAttribute("user", user);

        response.sendRedirect("dashboard.jsp"); // Redirect to the profile page with updated info
    }
}
