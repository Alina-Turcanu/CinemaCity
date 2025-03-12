package com.example.CinemaCity.Exceptions;

public class NoAvailableSeatsException extends RuntimeException{

    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
