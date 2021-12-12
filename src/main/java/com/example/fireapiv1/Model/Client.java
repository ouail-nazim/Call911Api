package com.example.fireapiv1.Model;

import com.example.fireapiv1.Helpers.State;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity(name = "Client")
@Table(name = "clients")
public class Client extends User {
    public Client() {
    }

    public Client(String first_name, String last_name, String email, String password, int reputation) {
        super(first_name, last_name, email, password);
        this.reputation = reputation;
    }

    @Column(name = "reputation")
    private int reputation;
    //---One to many with fires ----------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Fire> fires = new HashSet<>();
    //---One to many with posts ----------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Post> posts = new HashSet<>();
    //------------------------------------------------------------------
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "client_confirm_fire",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "fire_id")
    )
    Set<Fire> fireConfirmed = new HashSet<>();

    public Set<Fire> getFireConfirmed() {
        return fireConfirmed;
    }

    public void setFireConfirmed(Fire fire) {
        this.fireConfirmed.add(fire);
    }

    //------------------------------------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Scale> scales = new HashSet<>();

    public int getReputation() {
        return reputation;
    }

    public Set<Fire> getFires() {
        return fires;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    ///////////////////////////////////////////////////////////////////////
    //create fire----------------------------------------------------------
    public Fire createFire(Fire fire) {
        fire.setClient(this);
        return fire;
    }
    //comment--------------------------------------------------------------
    public Post comment(Post post, Fire fire) {
        post.setClient(this);
        post.setFire(fire);
        post.setCreatedAt(LocalDateTime.now());
        post.setCover_url(post.getCover_url() == null ? "https://via.placeholder.com/150" : post.getCover_url());
        return post;
    }
    //confirm--------------------------------------------------------------
    public HashMap<String , Object> confirmFire(Fire fire,State state){
        Scale scale = new Scale(this,fire,state);
        fire.setClientsConfirm(this);
        this.setFireConfirmed(fire);
        HashMap<String , Object> capitalCities = new HashMap<String , Object>();
        capitalCities.put("fire", fire);
        capitalCities.put("scale", scale);
        return capitalCities;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", dob=" + getDob() +
                ", age=" + getAge() +
                ", phone_number=" + getPhone_number() +
                ", reputation=" + this.reputation +
                '}';
    }
}
