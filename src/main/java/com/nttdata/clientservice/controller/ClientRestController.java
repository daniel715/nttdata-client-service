package com.nttdata.clientservice.controller;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.service.IClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

	@Autowired
    private IClientService clientService;

    @RequestMapping(value ="/list" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Client> findClients() {
    	return clientService.list();
    }

    @RequestMapping(value ="/find/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Single<Client> findById(@PathVariable String id) {
        return clientService.findById(id);
    }

    @PostMapping(value = "/save")
    public Single<Client> saveClient(@RequestBody Single<Client> productMono ){
        return clientService.saveClient(productMono);
    }

    @PutMapping(value = "/update/{id}")
    public Single<Client> updateCliente(@RequestBody Maybe<Client> productMono, @PathVariable String id){
        return clientService.updateClient(productMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Completable deleteCliente(@PathVariable String id){
        return clientService.deleteClient(id);
    }
}
