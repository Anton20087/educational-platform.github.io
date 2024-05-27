package org.example.schooldiary.api.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import java.util.List;

@Setter
@Getter
@Builder
public class SubjectRequestDto {
    private String name;
    private Long teacherId;
    private List<Long> studentIds;
}
