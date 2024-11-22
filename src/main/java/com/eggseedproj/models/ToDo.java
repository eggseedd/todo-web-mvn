package com.eggseedproj.models;

public class ToDo {
    private int id;
    private int userId;
    private String task;
    private String deadline;

    public ToDo(int id, int userId, String task, String deadline) {
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
