package com.example.fireapiv1.Requests;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthday;

    public RegisterRequest(String firstname, String lastname, String phone, String email, String password, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public Boolean validate() {
        String emailRegexPattern = "^(.+)@(\\S+)$";
        Boolean isEmailValid = patternMatches(this.getEmail(), emailRegexPattern);
        if (!isEmailValid) {
            return false;
        }
        return true;
    }
}
