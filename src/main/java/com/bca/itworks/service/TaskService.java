package com.bca.itworks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bca.itworks.model.Task;
import com.bca.itworks.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
    
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByCreatedDateDesc().forEach(tasks::add);

        if (tasks.isEmpty()) {
            log.info("you have no task");
            return new ArrayList<>();
        }

        return tasks;
    }

    public Task addTask(Task task) {
        return taskRepository.save(new Task(task.getTitle()));
    }

    public Task updateTask(String id) {
        Optional<Task> task = taskRepository.findById(UUID.fromString(id));
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setIsDone(!existingTask.getIsDone());
            existingTask.setModifiDate(new Date());
            return taskRepository.save(existingTask);
        } else {
            throw new IllegalArgumentException(String.format("task data with id %s not found.", id));
        }
    }

    public Task findById(String id) {
        Optional<Task> task = taskRepository.findById(UUID.fromString(id));
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new IllegalArgumentException(String.format("Task data with id %s not found.", id));
        }
    }


    // public Task findById(String id) {
    //     Task tasks = taskRepository.findById(UUID.fromString(id)).get();
    //     return tasks;
    // }
}