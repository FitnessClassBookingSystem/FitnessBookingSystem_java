package org.fitnessBooking.exception;

public class BookingDoesNotExist extends RuntimeException {
    public BookingDoesNotExist(String message) {
        super(message);
    }
}