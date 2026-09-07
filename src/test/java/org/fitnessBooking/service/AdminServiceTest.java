package org.fitnessBooking.service;

import org.fitnessBooking.dto.request.CreateBookingRequest;
import org.fitnessBooking.dto.response.CreateBookingResponse;
import org.fitnessBooking.model.Booking;
import org.fitnessBooking.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminServiceTest {

    @Autowired
    private BookingRepository bookingStorage;
    private AdminService adminService;
    @BeforeEach
    public void setUp() {
//        bookingStorage = new BookingRepository();
//        bookingStorage.deleteAll();

        adminService = new AdminServiceImpl(bookingStorage);
    }

    @Test
    public void createSessionTest() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Yoga");
        request.setDescription("Come over and stretch");
        request.setInstructor("Iron man");
        request.setDate(LocalDate.of(2027, 3, 1));
        request.setStartTime(LocalTime.of(5,10));
        request.setEndTime(LocalTime.of(7,10));
        adminService.newSession(request);
        CreateBookingResponse response = new CreateBookingResponse();
        response.setMessage("Success");
        assertEquals(3, bookingStorage.count());
    }
}
