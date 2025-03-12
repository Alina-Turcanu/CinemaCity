package com.example.CinemaCity.Exceptions;

    public class SeatAlreadyTakenException extends RuntimeException {
        public SeatAlreadyTakenException(String message) {
            super(message);
        }
    }
