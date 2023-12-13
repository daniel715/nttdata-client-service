package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface IClientService {

    Single<Client> saveClient(Single<Client> client);

    // read operation
    Flowable<Client> list();

    Single<Client> findByDni(String dni);

    // update operation
    Single<Client> updateClient(Maybe<Client> client, String clientId);

//    // delete operation
    Completable deleteClient(String clientId);

}
