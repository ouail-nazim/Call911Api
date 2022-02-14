package com.example.fireapiv1.Controller;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Token;
import com.example.fireapiv1.Repository.ClientRepository;
import com.example.fireapiv1.Requests.LoginRequest;
import com.example.fireapiv1.Requests.RegisterRequest;
import com.example.fireapiv1.Service.ClientService;
import com.example.fireapiv1.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final TokenService tokenService;


    @Autowired
    public AuthController(ClientService clientService, ClientRepository clientRepository, TokenService tokenService) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
    }

    /**
     * @return login
     */
    @PostMapping("/login")
    public HashMap<String, Object> Login(
            @RequestBody LoginRequest request
    ) {
        Optional<Client> client = clientService.ClientAttempt(request.getEmail(), request.getPassword());
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (client.isPresent()) {
            Token token = client.get().generateToken();
            tokenService.saveToken(token);
            data.put("token", token.getToken());
            data.put("client", client.get());
        }
        return data;
    }

    /**
     * @return register(" firstname ", " lastname ", " phone ", " email ", " password ", " birthday ")
     */
    @PostMapping("/register")
    public HashMap<String, Object> Register(
            @RequestBody RegisterRequest request
    ) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        Boolean isValid = request.validate();
        if (isValid) {
            Optional<Client> client = clientService.RegisterClient(request);
            if (client == null) {
                data.put("status", "errors");
            } else {
                if (client.isPresent() && client.get() instanceof Client) {
                    Token token = client.get().generateToken();
                    tokenService.saveToken(token);
                    data.put("token", token.getToken());
                    data.put("client", client.get());
                    data.put("status", "success");
                } else {
                    data.put("status", "errors");
                }
            }
        } else {
            data.put("status", "errors");
        }
        return data;
    }

}
