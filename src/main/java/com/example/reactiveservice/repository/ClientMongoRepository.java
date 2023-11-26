package com.example.reactiveservice.repository;

import com.example.reactiveservice.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientMongoRepository extends ReactiveMongoRepository<Client, String> {}
