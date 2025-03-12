package com.example.CinemaCity.Exceptions;

public class TicketAlreadyPurchasedException extends RuntimeException{

    public TicketAlreadyPurchasedException(String message) {
        super(message);
    }
}
