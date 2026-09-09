package org.fitnessBooking.service;

import org.fitnessBooking.model.Student;
import org.fitnessBooking.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
        studentService = new StudentServiceImpl(studentRepository);
    }
    @Test
    public void TestToCreateStudent() {

        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        studentService.createStudent(student);

        assertEquals(1, studentRepository.count());
    }

    }

