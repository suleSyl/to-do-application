package com.todoapp.taskmanager.repository;

import com.todoapp.taskmanager.models.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    User findByUsername(String username);
}
