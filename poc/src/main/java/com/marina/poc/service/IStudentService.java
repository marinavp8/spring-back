package com.marina.poc.service;

import com.marina.poc.model.Student;
import java.util.List;

public interface IStudentService {

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student, Long id);

    void deleteStudent(Long id);

    List<Student> getAllStudents();
}
