package org.example.schooldiary.api.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import org.example.schooldiary.entity.Subject;

import java.util.List;

@Setter
@Getter
@Builder
public class StudentRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private List<Subject> subjects;
    private int grade;
}
