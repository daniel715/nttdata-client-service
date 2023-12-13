package com.nttdata.clientservice.repository;

import com.nttdata.clientservice.model.Client;

import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientMongoRepository extends RxJava3CrudRepository<Client, String> {

    Single<Client> findByDni(String dni);

}
