package com.marina.poc.adapter.out.persistence;

import com.marina.poc.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}