package com.java.redis.demo.service;

import com.java.redis.demo.model.Task;

import java.util.Optional;

public interface TaskService {
    public Iterable<Task> getTasks();
    public Task addTask(Task task);
    public Task updateTask(Task task);
    public void deleteTask(int taskId);
    public Optional<Task> getTask(int taskId);
}
