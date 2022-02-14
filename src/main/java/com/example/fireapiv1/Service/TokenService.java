package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Token;
import com.example.fireapiv1.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token saveToken(Token token) {
        //TODO:FIX this
//        tokenRepository.deleteClientTokens(token.getClient().getId());
        return tokenRepository.save(token);
    }
}
