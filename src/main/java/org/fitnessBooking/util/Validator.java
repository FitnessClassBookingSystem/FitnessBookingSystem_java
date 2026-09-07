package org.fitnessBooking.util;

import org.fitnessBooking.dto.request.CancelBookingRequest;
import org.fitnessBooking.dto.request.CreateBookingRequest;
import org.fitnessBooking.dto.request.SearchSessionRequest;
import org.fitnessBooking.dto.request.UpdateBookingRequest;
import org.fitnessBooking.exception.BookingAllReadyExist;
import org.fitnessBooking.exception.BookingDoesNotExist;
import org.fitnessBooking.model.Booking;
import org.fitnessBooking.repository.BookingRepository;

public class Validator {

    private final BookingRepository sessionStorage;

    public Validator(BookingRepository sessionStorage) {
        this.sessionStorage = sessionStorage;
    }
    public void newSessionValidation(CreateBookingRequest createBooking) {
        Booking booking = sessionStorage.findByTitle(createBooking.getTitle().toLowerCase());
        if(booking != null){
            throw new BookingAllReadyExist("This session already exist");
        }
    }


    public void cancelSessionValidation(CancelBookingRequest cancelRequest) {
        Booking usedBooking = sessionStorage.findByTitle(cancelRequest.getTitle().toLowerCase());
        if(usedBooking == null){
            throw new BookingDoesNotExist("This session does not exist");
        }
    }

    public Booking updateSessionValidation(UpdateBookingRequest updateRequest) {
        Booking booking = sessionStorage.findByTitle(updateRequest.getOldTitle().toLowerCase());
        if(booking == null){
            throw new BookingDoesNotExist("This session does not exist");
        }

        return booking;
    }

    public Booking sessionSearchValidation(SearchSessionRequest searchRequest) {
        Booking findBooking = sessionStorage.findByTitle(searchRequest.getSessionTitle().toLowerCase());
        if(findBooking == null){
            throw new BookingDoesNotExist("This session does not exist");
        }

        return findBooking;
    }
}
