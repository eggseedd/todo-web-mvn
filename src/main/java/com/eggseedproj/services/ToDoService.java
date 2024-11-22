package com.eggseedproj.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eggseedproj.models.ToDo;
import com.eggseedproj.utils.Database;

public class ToDoService {
    public List<ToDo> getToDosByUser(int userId) {
        return getToDosByUserSorted(userId, null); // Default is no sorting
    }

    public List<ToDo> getToDosByUserSorted(int userId, String sortOrder) {
        List<ToDo> todos = new ArrayList<>();
        String orderBy = "ASC"; // Default to ascending order

        if ("desc".equalsIgnoreCase(sortOrder)) {
            orderBy = "DESC"; // Set to descending order if the user selected it
        }

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos WHERE user_id = ? ORDER BY deadline " + orderBy)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                todos.add(new ToDo(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("task"),
                        rs.getString("deadline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public List<ToDo> getToDosByUserAndDeadlineRange(int userId, String startDate, String endDate) {
        List<ToDo> todos = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos WHERE user_id = ? AND deadline BETWEEN ? AND ? ORDER BY deadline ASC")) {
            stmt.setInt(1, userId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                todos.add(new ToDo(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("task"),
                        rs.getString("deadline")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    public ToDo getToDoById(int userId, int taskId) {
        ToDo todo = null;
    
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos WHERE user_id = ? AND id = ?")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, taskId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                todo = new ToDo(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("task"),
                        rs.getString("deadline")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return todo;
    }    

    public void addNewTask(int userId, String task, String deadline) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos (user_id, task, deadline) VALUES (?, ?, ?)")) {
            stmt.setInt(1, userId);
            stmt.setString(2, task);
            stmt.setString(3, deadline);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToDoById(int id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM todos WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(int id, String task, String deadline) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE todos SET task = ?, deadline = ? WHERE id = ?")) {
            stmt.setString(1, task);
            stmt.setString(2, deadline);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
