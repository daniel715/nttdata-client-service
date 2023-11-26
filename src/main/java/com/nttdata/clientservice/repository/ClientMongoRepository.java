package com.nttdata.clientservice.repository;

import com.nttdata.clientservice.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientMongoRepository extends ReactiveMongoRepository<Client, String> {}
