package com.marina.poc.domain.port.in;

import com.marina.poc.domain.model.Student;
import java.util.List;

public interface StudentUseCase {
    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student, Long id);

    void deleteStudent(Long id);

    List<Student> getAllStudents();
}