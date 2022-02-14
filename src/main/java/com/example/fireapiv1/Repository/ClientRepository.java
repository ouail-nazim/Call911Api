package com.example.fireapiv1.Repository;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.email = :email AND c.password = :password")
    Optional<Client> findClientByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> findClientByEmail(@Param("email") String email);
}
