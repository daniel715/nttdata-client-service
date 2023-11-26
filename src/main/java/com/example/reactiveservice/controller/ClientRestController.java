package com.example.reactiveservice.controller;

import com.example.reactiveservice.model.Client;
import com.example.reactiveservice.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

	@Autowired
    private IClientService clientService;

    @RequestMapping(value ="/list" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Client> findClients() {
    	return clientService.list();
    }

    @RequestMapping(value ="/find/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Client> findById(@PathVariable String id) {
        return clientService.findById(id);
    }

    @PostMapping(value = "/save")
    public Mono<Client> saveClient(@RequestBody Mono<Client> productMono ){
        return clientService.saveClient(productMono);
    }

    @PutMapping(value = "/update/{id}")
    public Mono<Client> updateCliente(@RequestBody Mono<Client> productMono, @PathVariable String id){
        return clientService.updateClient(productMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCliente(@PathVariable String id){
        return clientService.deleteClient(id);
    }
}
