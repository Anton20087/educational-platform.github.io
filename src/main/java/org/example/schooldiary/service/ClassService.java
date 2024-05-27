package org.example.schooldiary.service;

import org.example.schooldiary.api.dto.ClassRequestDto;
import org.example.schooldiary.entity.Class;
import org.example.schooldiary.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<ClassRequestDto> getAllClassesWithStudentsCount() {
        List<Class> classes = classRepository.findAll();
        return classes.stream()
                .map(cls -> ClassRequestDto.builder()
                        .id(cls.getId())
                        .classNumber(cls.getClassNumber())
                        .studentsCount(cls.getStudentsCount())
                        .build())
                .collect(Collectors.toList());
    }
}
