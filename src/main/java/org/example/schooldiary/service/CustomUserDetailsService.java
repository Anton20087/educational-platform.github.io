package org.example.schooldiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String getUserRole(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection to database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "postgres", "first121212");

            String sql = "SELECT 'TEACHER' AS role FROM teachers WHERE email = ?" +
                    "UNION " +
                    "SELECT 'STUDENT' AS role FROM students WHERE email = ?" +
                    "UNION " +
                    "SELECT 'CHIEF_TEACHER' AS role FROM chief_teachers WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, email);

            // Execute the query
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("role");
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        } finally {
            // Close JDBC resources in finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection to database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "postgres", "first121212");

            String sql = "SELECT email, password, 'TEACHER' AS role FROM teachers WHERE email = ? " +
                    "UNION " +
                    "SELECT email, password, 'STUDENT' AS role FROM students WHERE email = ? " +
                    "UNION " +
                    "SELECT email, password, 'CHIEF_TEACHER' AS role FROM chief_teachers WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, email);

            // Execute the query
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");
                String role = resultSet.getString("role");

                // Build UserDetails object based on role
                switch (role) {
                    case "TEACHER":
                        return User.builder()
                                .username(userEmail)
                                .password(passwordEncoder.encode(userPassword))
                                .roles("TEACHER")
                                .build();
                    case "STUDENT":
                        return User.builder()
                                .username(userEmail)
                                .password(passwordEncoder.encode(userPassword))
                                .roles("STUDENT")
                                .build();
                    case "CHIEF_TEACHER":
                        return User.builder()
                                .username(userEmail)
                                .password(passwordEncoder.encode(userPassword))
                                .roles("CHIEF_TEACHER")
                                .build();
                    default:
                        throw new UsernameNotFoundException("Unknown role for user " + userEmail);
                }
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        } finally {
            // Close JDBC resources in finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
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
