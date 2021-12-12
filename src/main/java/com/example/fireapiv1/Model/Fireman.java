package com.example.fireapiv1.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity(name = "Fireman")
@Table(name = "firemans")
public class Fireman extends User {

    public Fireman() {}
    public Fireman(String first_name, String last_name, String email, String password, String departement) {
        super(first_name, last_name, email, password);
        this.departement = departement;
    }

    @Column(name = "departement")
    private String departement;
    public String getDepartement() {
        return departement;
    }
    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Fireman{" +
                "id=" + getId() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", dob=" + getDob() +
                ", age=" + getAge() +
                ", phone_number=" + getPhone_number() + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
