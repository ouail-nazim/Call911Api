package com.example.fireapiv1.Repository;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("DELETE FROM Token t WHERE t.client.id = :client_id")
    void deleteClientTokens(@Param("client_id") Long client);
}
