package com.java.redis.demo.controller;

import com.java.redis.demo.model.Task;
import com.java.redis.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("")
    public ResponseEntity<?> getTasks() {
        List<Task> tasks = new ArrayList<Task>();
        taskService.getTasks().forEach(tasks::add);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable int taskId) {
        Optional<Task> uTask = taskService.getTask(taskId);
        if (uTask.isPresent()) {
            Task eTask = uTask.get();
            eTask.setTitle(task.getTitle());
            eTask.setDescription(task.getDescription());
            eTask.setStatus(task.isStatus());
            return new ResponseEntity<>(taskService.updateTask(eTask), HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot update task", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Error err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
