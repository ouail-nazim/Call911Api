package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Repository.ClientRepository;
import com.example.fireapiv1.Requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> ClientAttempt(String email, String password) {
        return clientRepository.findClientByEmailAndPassword(email, password);
    }

    public Client CreateNewClient(RegisterRequest request) {
        Client u2 = new Client(request.getFirstname(),
                request.getLastname(), request.getEmail(),
                request.getPassword(),
                1);
        u2.setDob(request.getBirthday());
        u2.setPhone_number(request.getPhone());
        return u2;
    }

    public Optional<Client> RegisterClient(RegisterRequest request) {
        Optional<Client> client = clientRepository.findClientByEmail(request.getEmail());
        if (client.isPresent()) {
            return null;
        }
        Client c = CreateNewClient(request);
        c = clientRepository.save(c);
        return Optional.of(c);
    }
}
