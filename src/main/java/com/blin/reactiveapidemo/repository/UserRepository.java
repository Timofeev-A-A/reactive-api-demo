package com.blin.reactiveapidemo.repository;

import com.blin.reactiveapidemo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}