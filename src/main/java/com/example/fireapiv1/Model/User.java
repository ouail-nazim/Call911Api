package com.example.fireapiv1.Model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity(name = "User")
//@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(name = "phone_number")
    private String phone_number;

    @Transient
    private int age;


    public int getAge() {
        if (this.dob == null) {
            return -1;
        }
        return Period.between(this.dob, LocalDate.now()).getYears();

    }

    public User(Long id, String first_name, String last_name, String email, String password, LocalDate dob, String phone_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.phone_number = phone_number;
    }

    public User(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //Update password
    public boolean updatePassword(String old,String password){
        if (this.getPassword().equals(old)){
            this.setPassword(password);
            return true;
        }
        return false;
    }

}
