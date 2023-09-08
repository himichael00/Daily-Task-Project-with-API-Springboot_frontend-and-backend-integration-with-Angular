package com.bca.itworks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bca.itworks.model.Task;
import com.bca.itworks.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GraphQlResolver {

    @Autowired
    TaskService taskService;

    @QueryMapping(value = "task_by_id")
    public Task taskById(@Argument String id) {
        log.info("Getting task...");
        try {
            Task task = taskService.findById(id);
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
