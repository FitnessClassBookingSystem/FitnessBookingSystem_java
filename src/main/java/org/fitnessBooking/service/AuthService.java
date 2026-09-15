package org.fitnessBooking.service;

import org.fitnessBooking.model.Student;

public interface AuthService {
    Student login(String email, String password);
}
