package org.example.schooldiary.api.controller;

import org.example.schooldiary.api.dto.ClassRequestDto;
import org.example.schooldiary.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<ClassRequestDto> getAllClassesWithStudentsCount() {
        return classService.getAllClassesWithStudentsCount();
    }
}

