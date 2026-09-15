package org.fitnessBooking.service;

import org.fitnessBooking.Main;
import org.fitnessBooking.model.Student;
import org.fitnessBooking.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class AuthServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    private AuthService authService;

    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();

        authService = new AuthServiceImpl(studentRepository);
    }
    @Test
    public void TestToLoginStudent(){

        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("student");

        studentRepository.save(student);

        Student result = authService.login("oluyemi@gmail.com", "123456");

        assertEquals("Oluyemi", result.getName());
        assertEquals("oluyemi@gmail.com", result.getEmail());
    }

    @Test
    public void TestToLoginStudentWithWrongPassword(){
        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        studentRepository.save(student);

        Student result = authService.login("oluyemi", "000wrong");

        assertEquals(null, result);
    }

    @Test
    public void TestToLoginStudentThatDoesNotExist() {

        Student result = authService.login("unknown@gmail.com", "123456");

        assertEquals(null, result);
    }

}
