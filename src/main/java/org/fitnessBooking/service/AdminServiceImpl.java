package org.fitnessBooking.service;

import org.fitnessBooking.dto.request.CancelBookingRequest;
import org.fitnessBooking.dto.request.CreateBookingRequest;
import org.fitnessBooking.dto.request.SearchSessionRequest;
import org.fitnessBooking.dto.request.UpdateBookingRequest;
import org.fitnessBooking.dto.response.CancelBookingResponse;
import org.fitnessBooking.dto.response.CreateBookingResponse;
import org.fitnessBooking.dto.response.UpdateBookingResponse;
import org.fitnessBooking.model.Booking;
import org.fitnessBooking.repository.BookingRepository;
import org.fitnessBooking.util.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final BookingRepository sessionStorage;

    public AdminServiceImpl(BookingRepository sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @Override
    public CreateBookingResponse newSession(CreateBookingRequest createBooking){
        Validator validate = new Validator(sessionStorage);
        validate.newSessionValidation(createBooking);

        Booking book =  new Booking();
        book.setTitle(createBooking.getTitle());
        book.setDescription(createBooking.getDescription());
        book.setInstructor(createBooking.getInstructor());
        book.setDate(createBooking.getDate());
        book.setStartTime(createBooking.getStartTime());
        book.setEndTime(createBooking.getEndTime());
        sessionStorage.save(book);

        CreateBookingResponse createResponse = new CreateBookingResponse();
        createResponse.setMessage("Session created successfully");
        return createResponse;
    }

    @Override
    public CancelBookingResponse cancelBooking(CancelBookingRequest cancelRequest){
        Validator validate = new Validator(sessionStorage);
        validate.cancelSessionValidation(cancelRequest);

        sessionStorage.delete(sessionStorage.findByTitle(cancelRequest.getTitle().toLowerCase()));
        CancelBookingResponse cancelBookingResponse = new CancelBookingResponse();
        cancelBookingResponse.setMessage("Session cancelled successfully");
        return cancelBookingResponse;
    }

    @Override
    public UpdateBookingResponse updateBooking(UpdateBookingRequest updateRequest){
        Validator validate = new Validator(sessionStorage);
        Booking usedBooking = validate.updateSessionValidation(updateRequest);

        usedBooking.setTitle(updateRequest.getTitle());
        usedBooking.setDescription(updateRequest.getDescription());
        usedBooking.setInstructor(updateRequest.getInstructor());
        usedBooking.setDate(updateRequest.getDate());
        usedBooking.setStartTime(updateRequest.getStartTime());
        usedBooking.setEndTime(updateRequest.getEndTime());

        UpdateBookingResponse updateBookingResponse = new UpdateBookingResponse();
        updateBookingResponse.setMessage("Session updated successfully");
        return updateBookingResponse;
    }

    public List<Booking> viewAllSession() {
        return sessionStorage.findAll();
    }


    public Booking sessionSearch(SearchSessionRequest searchRequest){
        Validator validate = new Validator(sessionStorage);
        return validate.sessionSearchValidation(searchRequest);
    }
}
