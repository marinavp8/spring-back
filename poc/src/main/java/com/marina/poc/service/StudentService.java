package com.marina.poc.service;

import com.marina.poc.model.Student;
import com.marina.poc.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.marina.poc.Exception.StudentAlreadyExistsException;
import com.marina.poc.Exception.StudentNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {

        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new StudentAlreadyExistsException("Email already exists");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setDepartment(student.getDepartment());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
