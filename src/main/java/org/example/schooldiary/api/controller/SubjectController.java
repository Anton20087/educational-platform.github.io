package org.example.schooldiary.api.controller;

import org.example.schooldiary.api.dto.SubjectRequestDto;
import org.example.schooldiary.entity.Subject;
import org.example.schooldiary.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectRequestDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/teacher/{teacherId}")
    public List<SubjectRequestDto> getSubjectsByTeacher(@PathVariable Long teacherId) {
        return subjectService.getSubjectsByTeacher(teacherId);
    }

    @PostMapping("/{subjectId}/students/{studentId}")
    public ResponseEntity<Subject> addStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId) {
        Subject updatedSubject = subjectService.addStudentToSubject(subjectId, studentId);
        return ResponseEntity.ok(updatedSubject);
    }
}
