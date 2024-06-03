package org.example.schooldiary.service;

import org.example.schooldiary.api.dto.StudentRequestDto;
import org.example.schooldiary.api.dto.SubjectGradeRequestDto;
import org.example.schooldiary.entity.Student;
import org.example.schooldiary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentRequestDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(teacher -> StudentRequestDto.builder()
                        .name(teacher.getName())
                        .surname(teacher.getSurname())
                        .subjects(teacher.getSubjects())
                        .build())
                .collect(Collectors.toList());
    }

    public void setStudentSubjectGrade(Long studentId, SubjectGradeRequestDto subjectGradeDto) throws Exception{
        Optional<Student> optStudent = studentRepository.findById(studentId);
        if (!optStudent.isPresent()) {
            throw new Exception("Student not found");
        }
        Student student = optStudent.get();
        // Встановити оцінку зазначеного предмета для студента
        student.setGrade(subjectGradeDto.getGrade());
        studentRepository.save(student);
    }
}

