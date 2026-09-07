package org.fitnessBooking.dto.request;

import lombok.Data;
import org.fitnessBooking.model.Booking;

import java.util.List;

@Data
public class ViewAllSessionRequest {

    List<Booking> sessions;
}