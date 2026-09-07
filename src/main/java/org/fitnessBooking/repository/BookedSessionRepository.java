package org.fitnessBooking.repository;

import org.fitnessBooking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookedSessionRepository extends MongoRepository<Booking, String> {
}
