package com.example.fireapiv1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Fire")
@Table(name = "fires")
public class Fire {
    public Fire() {
    }

    public Fire(Double latitude, Double longitude, String street, String city, String zip, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.createdAt = LocalDate.now();
    }
    public Fire(Double latitude, Double longitude, String street, String city, String zip, String country, Client client) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.createdAt = LocalDate.now();
        this.client = client;
    }

    @Id
    @SequenceGenerator(name = "fire_sequence", sequenceName = "fire_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "fire_sequence")
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;
    @Column(name = "longitude", nullable = false)
    private Double longitude;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zip", nullable = false)
    private String zip;
    @Column(name = "country", nullable = false)
    private String country;
    @Transient
    private String state;

    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "controled_at")
    private LocalDate controledAt;

    //---many to one with clients ----------------------------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    //---One to many with posts ----------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "fire")
    private Set<Post> posts=new HashSet<>();

    //---One to many with scales ----------------------------------------
    @JsonIgnore
    @OneToMany(mappedBy = "fire")
    private Set<Scale> scales=new HashSet<>();

    public Set<Scale> getScales() {
        return scales;
    }

    public void setScales(Set<Scale> scales) {
        this.scales = scales;
    }

    //-------------------------------------------------------------------
    @JsonIgnore
    @ManyToMany(mappedBy = "fireConfirmed")
    private Set<Client> clientsConfirm = new HashSet<>();

    public Set<Client> getClientsConfirm() {
        return clientsConfirm;
    }

    public void setClientsConfirm(Client clientsConfirm) {
        this.clientsConfirm.add(clientsConfirm);
    }

    public int getCountConfirmed(){
        return this.getClientsConfirm().size();
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        try {
            return this.zip.substring(0, 2);
        } catch (Exception e) {
            return null;
        }
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getControledAt() {
        return controledAt;
    }

    public void setControledAt(LocalDate controledAt) {
        this.controledAt = controledAt;
    }

    @Override
    public String toString() {
        return "Fire{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                ", controledAt=" + controledAt +
                ", client=" + client +
                '}';
    }
}
