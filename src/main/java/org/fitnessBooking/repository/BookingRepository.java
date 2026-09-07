package org.fitnessBooking.repository;

import org.fitnessBooking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
    Booking findByTitle(String title);
}
