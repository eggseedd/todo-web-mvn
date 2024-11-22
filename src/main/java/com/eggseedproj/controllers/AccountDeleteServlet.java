package com.eggseedproj.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eggseedproj.models.User;
import com.eggseedproj.services.UserService;

public class AccountDeleteServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Delete the user account
        userService.deleteUser(user.getId());

        // Invalidate the session after account deletion
        session.invalidate();

        response.sendRedirect("login.jsp"); // Redirect to the login page after deletion
    }
}
