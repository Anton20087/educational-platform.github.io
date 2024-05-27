package org.example.schooldiary.api.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Builder
public class ClassRequestDto {
    private String classNumber;
    private Long id;
    private int studentsCount;
}
