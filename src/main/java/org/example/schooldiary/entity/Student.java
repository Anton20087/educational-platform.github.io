package org.example.schooldiary.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String password;
    private String email;
    private int grade;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;
}

