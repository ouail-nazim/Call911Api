package com.example.fireapiv1.Model;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "token_sequence")
    private Long id;
    @Column(name = "token")
    private String token;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "expired_at")
    private LocalDate expiredAt;
    //---many to one with clients ----------------------------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public Token(Client client) {
        this.token = "Bearer " + UUID.randomUUID().toString();
        this.createdAt = LocalDate.now();
        this.expiredAt = null;
        this.client = client;
    }

    public Token() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", createdAt=" + createdAt +
                ", expiredAt=" + expiredAt +
                ", client=" + client +
                '}';
    }
}
