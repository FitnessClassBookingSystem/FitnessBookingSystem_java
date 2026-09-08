package org.fitnessBooking.service;

import org.fitnessBooking.dto.request.CancelBookingRequest;
import org.fitnessBooking.dto.request.CreateBookingRequest;
import org.fitnessBooking.exception.RequestError;
import org.fitnessBooking.model.Booking;
import org.fitnessBooking.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private BookingRepository bookingStorage;
    private AdminService adminService;
    @BeforeEach
    public void setUp() {
        adminService = new AdminServiceImpl(bookingStorage);
    }

    @Captor
    private ArgumentCaptor<Booking> bookingCaptor;
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

        verify(bookingStorage).save(bookingCaptor.capture());
        Booking booking = bookingCaptor.getValue();
        assertEquals("Yoga", booking.getTitle());
    }


    @Test
    public void createSession_sessionTitleCannotBeEmpty_test() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("   ");
        assertThrows(RequestError.class,
                () -> adminService.newSession(request));
    }

    @Test
    public void createSession_sessionTitleLength_minimumOfThreeTest(){
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Ac");
        assertThrows(RequestError.class,
                () -> adminService.newSession(request));
    }


    @Test
    public void createSession_sessionTitleLength_maximumOfTwenty_test(){
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Yogaideyonmyownaaaaideywayamakenobodyillmyyogayoga");
        assertThrows(RequestError.class,
                () -> adminService.newSession(request));
    }

    @Test
    public void createSession_sessionDateMustBeUpcoming_test() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Yoga");
        request.setDescription("Come over and stretch");
        request.setInstructor("Iron man");
        request.setDate(LocalDate.of(2026, 3, 1));
        assertThrows(RequestError.class,
                () -> adminService.newSession(request));
    }

    @Test
    public void createSession_sessionEndTimeMustBeAfter_StartTimeTest() {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Yoga");
        request.setDescription("Come over and stretch");
        request.setInstructor("Iron man");
        request.setDate(LocalDate.of(2027, 3, 1));
        request.setStartTime(LocalTime.of(5,10));
        request.setEndTime(LocalTime.of(4,10));
        assertThrows(RequestError.class,
                () -> adminService.newSession(request));
    }

    @Test
    public void createSession_cancelSession_test(){
        CreateBookingRequest request = new CreateBookingRequest();
        request.setTitle("Leg day");
        request.setDescription("Come over and stretch");
        request.setInstructor("Iron man");
        request.setDate(LocalDate.of(2027, 3, 1));
        request.setStartTime(LocalTime.of(5,10));
        request.setEndTime(LocalTime.of(7,10));
        adminService.newSession(request);

        CancelBookingRequest cancelRequest = new CancelBookingRequest();
        cancelRequest.setTitle("Leg day");
        adminService.cancelBooking(cancelRequest);

        verify(bookingStorage).delete(bookingCaptor.capture());
        Booking booking = bookingCaptor.getValue();
        assertEquals("Leg day", booking.getTitle());
    }
}