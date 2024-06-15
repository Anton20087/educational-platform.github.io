package org.example.schooldiary.service;

import org.example.schooldiary.entity.Student;
import org.example.schooldiary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(int id) throws UsernameNotFoundException {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        PreparedStatement deleteStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection to database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "postgres", "first121212");

            // Select student details
            String selectSql = "SELECT email, password, role FROM public.students WHERE id = ?";
            selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, id);
            resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");
                String role = resultSet.getString("role");

                // Delete student
                String deleteSql = "DELETE FROM public.students WHERE id = ?";
                deleteStatement = connection.prepareStatement(deleteSql);
                deleteStatement.setInt(1, id);
                deleteStatement.executeUpdate();

                // Build UserDetails object
                User.builder()
                        .username(userEmail)
                        .password(passwordEncoder.encode(userPassword))
                        .roles(role) // Role can be STUDENT or any other relevant role
                        .build();
            } else {
                throw new UsernameNotFoundException("Student not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        } finally {
            // Close JDBC resources in finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (deleteStatement != null) {
                    deleteStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Log the exception or handle it appropriately
                e.printStackTrace();
            }
        }
    }
}
