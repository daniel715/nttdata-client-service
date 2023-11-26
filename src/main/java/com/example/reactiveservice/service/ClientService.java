package com.example.reactiveservice.service;

import com.example.reactiveservice.model.Client;
import com.example.reactiveservice.repository.ClientMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientMongoRepository clientRepository;

    @Override
    public Flux<Client> list() {
        return this.clientRepository.findAll();

    }

    @Override
    public Mono<Client> findById(String id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public Mono<Client> saveClient(Mono<Client> client) {
        return client.flatMap(clientRepository::insert);
    }

    @Override
    public Mono<Client> updateClient(Mono<Client> client, String clientId) {
        return clientRepository.findById(clientId)
                .flatMap(p -> client)
                .doOnNext(e -> e.setId(clientId))
                .flatMap(clientRepository::save);
    }

    @Override
    public Mono<Void> deleteClient(String clientId) {
        return clientRepository.deleteById(clientId);
    }

}
