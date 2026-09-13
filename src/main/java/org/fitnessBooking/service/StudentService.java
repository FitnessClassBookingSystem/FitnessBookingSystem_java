package org.fitnessBooking.service;

import org.fitnessBooking.model.Student;

public interface StudentService {
    Student createStudent(Student student);
    Student findStudentByEmail(String email);
    Student findStudentById(String id);
    Student deleteStudent(String id);
}
