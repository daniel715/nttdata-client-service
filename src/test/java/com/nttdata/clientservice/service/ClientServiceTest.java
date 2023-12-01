package com.nttdata.clientservice.service;

import com.nttdata.clientservice.model.Client;
import com.nttdata.clientservice.repository.ClientMongoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    private ClientService clientService;

    @Mock
    private ClientMongoRepository clientMongoRepository;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientMongoRepository);
    }

    @Test
    @DisplayName("Test de encontrar client por id")
    public void testFindClientById() {
        Client expected = new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone");

        Mockito.when( clientMongoRepository.findById(Mockito.anyString()) ).thenReturn(Mono.just(new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone")));

        Mono<Client> response = clientService.findById("1");

        Assertions.assertEquals(expected.getId(),response.block().getId());
        Assertions.assertEquals(expected.getName(),response.block().getName());
        Assertions.assertEquals(expected.getLastname(),response.block().getLastname());
        Assertions.assertEquals(expected.getEmail(),response.block().getEmail());
        Assertions.assertEquals(expected.getPassword(),response.block().getPassword());
        Assertions.assertEquals(expected.getCreatedAt(),response.block().getCreatedAt());
        Assertions.assertEquals(expected.getAddress(),response.block().getAddress());
        Assertions.assertEquals(expected.getCity(),response.block().getCity());
        Assertions.assertEquals(expected.getCountry(),response.block().getCountry());
        Assertions.assertEquals(expected.getTelephone(),response.block().getTelephone());

        verify(clientMongoRepository, times(1)).findById(Mockito.anyString());

    }

    @Test
    @DisplayName("Test for obtener todos from clientes")
    public void testFindAll() {
        ArrayList<Client> expected = new ArrayList<>();
        expected.add(new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone"));
        expected.add(new Client("2", "Andrea", "Salazar", "andrea@gmail.com", "pass",
                "createdAt", "address", "city", "country", "telephone"));


        ArrayList<Client> clientsData = new ArrayList<>();
        clientsData.add(new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone"));
        clientsData.add(new Client("2", "Andrea", "Salazar", "andrea@gmail.com", "pass",
                "createdAt", "address", "city", "country", "telephone"));

       Flux<Client> clients = Flux.fromIterable(clientsData);

       Mockito.when(clientMongoRepository.findAll()).thenReturn(clients);

       Flux<Client> actual = clientService.list();

        StepVerifier.create(actual)
                .expectNext(expected.get(1))
                .expectComplete();

    }


    @Test
    @DisplayName("Test de guardar client")
    public void testSave() {
        Client expected = new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone");

        Mockito.when( clientMongoRepository.insert((Client) Mockito.any()))
                .thenReturn( Mono.just(new Client("1", "Daniel", "Montellanos", "email", "pass",
                        "createdAt", "address", "city", "country", "telephone")) );

        Mono<Client> actual = clientService.saveClient(Mono.just(new Client("1", "Daniel", "Montellanos", "email", "pass",
                "createdAt", "address", "city", "country", "telephone")));

        Assertions.assertEquals(expected.getId(),actual.block().getId());
        Assertions.assertEquals(expected.getName(),actual.block().getName());
        Assertions.assertEquals(expected.getLastname(),actual.block().getLastname());
        Assertions.assertEquals(expected.getEmail(),actual.block().getEmail());
        Assertions.assertEquals(expected.getPassword(),actual.block().getPassword());
        Assertions.assertEquals(expected.getCreatedAt(),actual.block().getCreatedAt());
        Assertions.assertEquals(expected.getAddress(),actual.block().getAddress());
        Assertions.assertEquals(expected.getCity(),actual.block().getCity());
        Assertions.assertEquals(expected.getCountry(),actual.block().getCountry());
        Assertions.assertEquals(expected.getTelephone(),actual.block().getTelephone());
    }
    @Test
    @DisplayName("Test de borrar un cliente por id")
    public void testDeleteByIdReturnTrue() {

        String id = "1";
        when(clientMongoRepository.deleteById(id)).thenReturn(Mono.empty());
        clientService.deleteClient(id);
        verify(clientMongoRepository,times(1)).deleteById(id);

    }
}