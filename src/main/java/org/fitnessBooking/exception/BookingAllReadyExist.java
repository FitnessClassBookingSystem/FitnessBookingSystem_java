package org.fitnessBooking.exception;

public class BookingAllReadyExist extends RuntimeException {
    public BookingAllReadyExist(String message) {
        super(message);
    }
}
