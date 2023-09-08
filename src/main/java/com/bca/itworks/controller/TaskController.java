package com.bca.itworks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bca.itworks.model.Task;
import com.bca.itworks.service.TaskService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    // autowired digunakan untuk inject otomatis taskService ke class TaskController
    @Autowired
    TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<Task>> getAll() {
        log.info("Getting task list...");
        try {
            List<Task> tasks = taskService.getAll();
            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {
            if (task.getTitle() == null || !StringUtils.hasText(task.getTitle())) {
                log.info("to add item in your list, pls give it a title");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("")
    public ResponseEntity<Task> updateTask(@RequestBody Map<String, String> body) {
        try {
            if (body.get("id") == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
            return new ResponseEntity<>(taskService.updateTask(body.get("id")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @QueryMapping
    @GetMapping("/list-of-tasks")
    public List<Task> tasks() {
        log.info("Getting task list...");
        try {
            List<Task> tasks = taskService.getAll();
            return tasks;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
