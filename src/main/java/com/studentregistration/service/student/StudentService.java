package com.studentregistration.service.student;

import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * @author Oluwafemi Fagbemi
 */
public interface StudentService {
    Student save(StudentDto studentDto) throws EntityException;
    Student getStudent(String studentRegNo) throws EntityNotFoundException;
    Student getStudent(UUID rrn) throws EntityNotFoundException;
    Student update(StudentDto studentDto) throws EntityNotFoundException;
    int deleteStudent(Long studentId);
    List<Student> filterStudentByCourse(String courseCode);
    List<Student> filterStudentByCourse();


}
