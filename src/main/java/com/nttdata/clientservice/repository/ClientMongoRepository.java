package com.nttdata.clientservice.repository;

import com.nttdata.clientservice.model.Client;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientMongoRepository extends RxJava3CrudRepository<Client, String> {}
