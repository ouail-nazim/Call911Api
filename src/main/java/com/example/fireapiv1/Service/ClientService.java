package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

}
