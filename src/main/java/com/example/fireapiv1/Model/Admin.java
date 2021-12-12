package com.example.fireapiv1.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Admin")
@Table(name = "admins")
public class Admin extends User {

    public Admin() {}
    public Admin(String first_name, String last_name, String email, String password, String ipAddress) {
        super(first_name, last_name, email, password);
        this.ipAddress = ipAddress;
    }

    @Column(name = "ip_address")
    private String ipAddress;
    public String getipAddress() {
        return ipAddress;
    }
    public void setipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", dob=" + getDob() +
                ", age=" + getAge() +
                ", phone_number=" + getPhone_number() + '\'' +
                ", Ip Address='" + ipAddress + '\'' +
                '}';
    }
}
