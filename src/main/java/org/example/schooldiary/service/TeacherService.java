package org.example.schooldiary.service;

import org.example.schooldiary.api.dto.TeacherRequestDto;
import org.example.schooldiary.entity.Teacher;
import org.example.schooldiary.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherRequestDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacher -> TeacherRequestDto.builder()
                        .name(teacher.getName())
                        .surname(teacher.getSurname())
                        .subjects(teacher.getSubjects())
                        .build())
                .collect(Collectors.toList());
    }

    public Teacher addTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequestDto.getName());
        teacher.setSurname(teacherRequestDto.getSurname());
        teacher.setSubjects(teacherRequestDto.getSubjects());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
