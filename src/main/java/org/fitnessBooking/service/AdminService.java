package org.fitnessBooking.service;

import org.fitnessBooking.dto.request.CancelBookingRequest;
import org.fitnessBooking.dto.request.CreateBookingRequest;
import org.fitnessBooking.dto.request.UpdateBookingRequest;
import org.fitnessBooking.dto.response.CancelBookingResponse;
import org.fitnessBooking.dto.response.CreateBookingResponse;
import org.fitnessBooking.dto.response.UpdateBookingResponse;

public interface AdminService {

    CreateBookingResponse newSession(CreateBookingRequest createBooking);

    CancelBookingResponse cancelBooking(CancelBookingRequest cancelBooking);

    UpdateBookingResponse updateBooking(UpdateBookingRequest updateRequest);
}
