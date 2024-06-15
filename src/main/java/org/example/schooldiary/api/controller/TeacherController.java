package org.example.schooldiary.api.controller;

import org.example.schooldiary.api.dto.TeacherRequestDto;
import org.example.schooldiary.entity.Teacher;
import org.example.schooldiary.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping
    public List<TeacherRequestDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        return teacherService.addTeacher(teacherRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }
}


