package com.example.ticketservice.dto.response;

public class PersonResponse {

    String name;
    String surname;
    String age;
    String password;

    public PersonResponse(String name, String surname, String age, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
    }
}