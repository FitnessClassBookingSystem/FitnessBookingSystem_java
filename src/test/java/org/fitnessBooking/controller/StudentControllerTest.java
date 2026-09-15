package org.fitnessBooking.controller;

import org.fitnessBooking.model.Student;
import org.fitnessBooking.repository.StudentRepository;
import org.fitnessBooking.service.StudentService;
import org.fitnessBooking.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    private StudentService studentService;

    private StudentController controller;

    @BeforeEach
    public void setUp() {

        studentRepository.deleteAll();

        studentService = new StudentServiceImpl(studentRepository);

        controller = new StudentController(studentService);
    }

    @Test
    public void TestToCreateStudent() {

        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        Student result =
                controller.createStudent(student);

        assertEquals("Oluyemi", result.getName());
        assertEquals("oluyemi@gmail.com", result.getEmail());
    }

    @Test
    public void TestToFindStudentByEmail(){
        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        studentService.createStudent(student);

        Student result = controller.findStudentByEmail("oluyemi@gmail.com");

        assertEquals("Oluyemi", result.getName());
        assertEquals("oluyemi@gmail.com", result.getEmail());
    }

    @Test
    public void TestToFindStudentById(){

        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        Student savedStudent = controller.createStudent(student);

        Student result = controller.findStudentById(savedStudent.getId());

        assertEquals("Oluyemi", result.getName());
        assertEquals("oluyemi@gmail.com", result.getEmail());
    }

    @Test
    public void TestToDeleteStudent(){

        Student student = new Student();

        student.setName("Oluyemi");
        student.setEmail("oluyemi@gmail.com");
        student.setPassword("123456");
        student.setRole("STUDENT");

        Student savedStudent = controller.createStudent(student);

        Student result = controller.deleteStudent(savedStudent.getId());

        assertEquals("Oluyemi", result.getName());
        assertEquals(0, studentRepository.count());
    }
}

