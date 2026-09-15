package org.fitnessBooking.controller;

import org.fitnessBooking.model.Student;
import org.fitnessBooking.service.StudentService;

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student createStudent(Student student) {
        return studentService.createStudent(student);
    }

    public Student findStudentByEmail(String email) {
        return studentService.findStudentByEmail(email);
    }

    public Student findStudentById(String id){
        return studentService.findStudentById(id);
    }
    public Student deleteStudent(String id) {
        return studentService.deleteStudent(id);
    }
}
