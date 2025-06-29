package com.marina.poc.application;

import com.marina.poc.domain.model.Student;
import com.marina.poc.domain.port.in.StudentUseCase;
import com.marina.poc.domain.port.out.StudentRepositoryPort;
import com.marina.poc.domain.exception.StudentAlreadyExistsException;
import com.marina.poc.domain.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentUseCase {
    private final StudentRepositoryPort studentRepository;

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