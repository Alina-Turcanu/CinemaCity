package com.example.CinemaCity.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue
    private Long id;


}
