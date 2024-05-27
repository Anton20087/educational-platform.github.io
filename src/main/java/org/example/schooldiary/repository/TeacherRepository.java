package org.example.schooldiary.repository;

import org.example.schooldiary.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findBySurname(String surname);
    Teacher findFirstById(Long id);
}
