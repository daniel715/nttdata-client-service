package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {

    Mono<Client> saveClient(Mono<Client> client);

    // read operation
    Flux<Client> list();

    Mono<Client> findById(String id);

    // update operation
    Mono<Client> updateClient(Mono<Client> client, String clientId);

    // delete operation
    Mono<Void> deleteClient(String clientId);

}
