package org.fitnessBooking.service;

import org.fitnessBooking.model.Student;
import org.fitnessBooking.repository.StudentRepository;

public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;

    public AuthServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student login(String Email, String password){

        Student student = studentRepository.findByEmail(Email);

        if(student != null && student.getPassword().equals(password)){
            return student;
        }
        return null;
    }
}
