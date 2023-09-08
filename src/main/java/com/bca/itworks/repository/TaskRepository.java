package com.bca.itworks.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.itworks.model.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
    // get task by id
    Optional<Task> findById(UUID id);


    // get all task, similiar with "SELECT * FROM task;"
    // included in jpa
    
    List<Task> findAllByOrderByCreatedDateDesc();
}
