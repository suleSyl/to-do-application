package com.todoapp.taskmanager.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.taskmanager.models.Task;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "task")
public interface TaskRepository extends CouchbaseRepository<Task, Integer> {

}
