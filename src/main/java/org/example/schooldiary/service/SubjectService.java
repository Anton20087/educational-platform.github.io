package org.example.schooldiary.service;

import org.example.schooldiary.api.dto.SubjectRequestDto;
import org.example.schooldiary.entity.Student;
import org.example.schooldiary.entity.Subject;
import org.example.schooldiary.repository.StudentRepository;
import org.example.schooldiary.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    // Метод для отримання всіх предметів
    public List<SubjectRequestDto> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(subject -> SubjectRequestDto.builder()
                        .name(subject.getName())
                        .teacherId(subject.getTeacher().getId())
                        .studentIds(subject.getStudents().stream()
                                .map(Student::getId)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    // Метод для отримання предметів за вчителем
    public List<SubjectRequestDto> getSubjectsByTeacher(Long teacherId) {
        List<Subject> subjects = subjectRepository.findByTeacherId(teacherId);
        return subjects.stream()
                .map(subject -> SubjectRequestDto.builder()
                        .name(subject.getName())
                        .teacherId(subject.getTeacher().getId())
                        .studentIds(subject.getStudents().stream()
                                .map(Student::getId)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    // Метод для додавання студента до предмету
    public Subject addStudentToSubject(Long subjectId, Long studentId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();

        subject.getStudents().add(student);
        return subjectRepository.save(subject);
    }
}
