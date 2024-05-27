package org.example.schooldiary.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SubjectGradeRequestDto {
    private String subject;
    private int grade;
}
