package com.java.redis.demo.service;

import com.java.redis.demo.model.Task;
import com.java.redis.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;


    @Override
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.delete(new Task(taskId));
    }

    @Override
    public Optional<Task> getTask(int taskId) {
        return taskRepository.findById(taskId);
    }
}
