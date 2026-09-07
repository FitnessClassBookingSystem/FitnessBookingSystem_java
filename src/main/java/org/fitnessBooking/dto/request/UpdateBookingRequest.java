package org.fitnessBooking.dto.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateBookingRequest {

    @Id
    private String id;
    private String oldTitle;
    private String title;
    private String description;
    private String instructor;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}