package org.fitnessBooking.repository;

import org.fitnessBooking.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByEmail(String email);
}
