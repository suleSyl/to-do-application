package com.todoapp.taskmanager.repository;

import com.todoapp.taskmanager.models.Task;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CouchbaseRepository<Task, String> {
    List<Task> findByCreatedBy(String createdBy);
}
