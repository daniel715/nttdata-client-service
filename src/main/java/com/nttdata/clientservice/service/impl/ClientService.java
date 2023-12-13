package com.nttdata.clientservice.service.impl;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.repository.ClientMongoRepository;
import com.nttdata.clientservice.service.IClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientMongoRepository clientRepository;

    @Override
    public Flowable<Client> list() {
        return this.clientRepository.findAll();
    }

    @Override
    public Single<Client> findByDni(String dni) {
        return this.clientRepository.findByDni(dni);
    }

    @Override
    public Single<Client> saveClient(Single<Client> client) {
        return client.flatMap(clientRepository::save);
    }

    @Override
    public Single<Client> updateClient(Maybe<Client> client, String clientId) {

        return clientRepository.findById(clientId)
                .flatMap(p -> client)
                .doOnSuccess(e -> e.set_id(clientId)).toSingle()
                .flatMap(clientRepository::save);
    }

    @Override
    public Completable deleteClient(String clientId) {
        return clientRepository.deleteById(clientId);
    }

}
