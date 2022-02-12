package com.example.fireapiv1.Model;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Post")
@Table(name = "posts")
public class Post {
    public Post() {}


    public Post(String comment, String cover_url, Client client) {
        this.comment = comment;
        this.cover_url = cover_url;
        this.client = client;
        this.createdAt = LocalDateTime.now();
    }
    public Post(String comment, Client client) {
        this.comment = comment;
        this.client = client;
        this.cover_url="https://via.placeholder.com/450";
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "post_sequence")
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "cover_url")
    private String cover_url;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //---Many to one with clients ----------------------------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    //------------------------------------------------------------------
    //---many to one with fires ----------------------------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fire_id", referencedColumnName = "id")
    private Fire fire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public Client getClient() {
        return client;
    }

    public String getComment() {
        return comment;
    }

    public String getCover_url() {
        return cover_url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", cover_url='" + cover_url + '\'' +
                ", createdAt=" + createdAt +
                ", client=" + client +
                '}';
    }
}
