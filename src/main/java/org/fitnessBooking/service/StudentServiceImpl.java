package org.fitnessBooking.service;

import org.fitnessBooking.model.Student;
import org.fitnessBooking.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student findStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student deleteStudent(String id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student != null) {
            studentRepository.deleteById(id);
        }

        return student;
    }
}