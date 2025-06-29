package com.marina.poc.domain.port.out;

import com.marina.poc.domain.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepositoryPort {
    Student save(Student student);

    Optional<Student> findById(Long id);

    Optional<Student> findByEmail(String email);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Student> findAll();
}