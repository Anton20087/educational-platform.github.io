package org.example.schooldiary.api.controller;

import org.example.schooldiary.api.dto.StudentRequestDto;
import org.example.schooldiary.api.dto.SubjectGradeRequestDto;
import org.example.schooldiary.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<StudentRequestDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/{studentId}/grades")
    public ResponseEntity<String> setStudentSubjectGrade(@PathVariable Long studentId, @RequestBody SubjectGradeRequestDto subjectGradeRequestDto) {
        try {
            studentService.setStudentSubjectGrade(studentId, subjectGradeRequestDto);
            return ResponseEntity.ok("Grade updated successfully");
        } catch (Exception e) {
            if (e.getMessage().equals("Student not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update grade: " + e.getMessage());
        }
    }
}
