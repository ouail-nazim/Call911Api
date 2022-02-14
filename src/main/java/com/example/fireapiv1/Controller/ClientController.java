package com.example.fireapiv1.Controller;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Fire;
import com.example.fireapiv1.Repository.ClientRepository;
import com.example.fireapiv1.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    /**
     * @return list of users
     */
    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

}
