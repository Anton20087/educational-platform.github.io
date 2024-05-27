package org.example.schooldiary.api.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import org.example.schooldiary.entity.Subject;

import java.util.List;

@Setter
@Getter
@Builder
public class TeacherRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Subject> subjects;
}

