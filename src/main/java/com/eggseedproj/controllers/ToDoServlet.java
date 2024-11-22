package com.eggseedproj.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eggseedproj.models.ToDo;
import com.eggseedproj.models.User;
import com.eggseedproj.services.ToDoService;

public class ToDoServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Get the full user object
        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            return;
        }
    
        Integer userId = user.getId(); // Extract userId from the user object
        String action = request.getParameter("action");
    
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            toDoService.deleteToDoById(id);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String task = request.getParameter("task");
            String deadline = request.getParameter("deadline");
            toDoService.updateTask(id, task, deadline); // Update the task
        } else {
            String task = request.getParameter("task");
            String deadline = request.getParameter("deadline");
            toDoService.addNewTask(userId, task, deadline);
        }
    
        // Refresh the tasks after update/delete
        List<ToDo> todos = toDoService.getToDosByUser(userId);
        session.setAttribute("todos", todos); // Set tasks in the session
        response.sendRedirect("dashboard"); // Redirect to the dashboard to refresh the page
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // Get the full user object
        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            return;
        }
    
        Integer userId = user.getId(); // Extract userId from the user object
    
        String action = request.getParameter("action");
        List<ToDo> todos = new ArrayList<>();
    
        if ("edit".equals(action)) {
            // Get the ID of the task to be edited
            int taskId = Integer.parseInt(request.getParameter("id"));
            // Retrieve the task by ID
            ToDo todo = toDoService.getToDoById(userId, taskId);
            if (todo != null) {
                // Set the task in request attributes for editing
                request.setAttribute("todo", todo);
                request.getRequestDispatcher("/editTask.jsp").forward(request, response); // Forward to edit task page
            } else {
                // If task is not found, redirect or show an error
                response.sendRedirect("dashboard"); // Redirect to dashboard if task not found
            }
            return;
        }
    
        // Existing code to handle sorting, filtering, etc.
        String sortOrder = request.getParameter("sort");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
    
        if ("clear".equals(request.getParameter("action"))) {
            todos = toDoService.getToDosByUser(userId); // Fetch all tasks if filter is cleared
        } else if (startDate != null && endDate != null) {
            todos = toDoService.getToDosByUserAndDeadlineRange(userId, startDate, endDate);
        } else if (sortOrder != null) {
            todos = toDoService.getToDosByUserSorted(userId, sortOrder); // Fetch sorted tasks
        } else {
            todos = toDoService.getToDosByUser(userId); // Fetch unsorted tasks
        }
    
        session.setAttribute("todos", todos); // Set tasks in the session
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response); // Forward to the dashboard page
    }
    

}
