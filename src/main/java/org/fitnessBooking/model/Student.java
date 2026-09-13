package org.fitnessBooking.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Data
public class Student {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String role;
}