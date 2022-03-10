package com.studentregistration.service.student;


import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.domain.repository.CourseRepository;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.domain.repository.StudentRepository;
import com.studentregistration.exception.DuplicateEntityException;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Oluwafemi Fagbemi
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private ModelMapper mapper=new ModelMapper();





    @Override
    public Student save(StudentDto studentDto) throws EntityException {
        Optional<Student> studentInDb = Optional.ofNullable(this.studentRepository.findByStudentRegistrationNo(studentDto.getStudentRegistrationNo()));
        if (studentInDb.isPresent()) {
            throw new DuplicateEntityException(Student.class, "StudentRegistrationNo", studentDto.getStudentRegistrationNo().toString());
        }
        Student student = mapper.map(studentDto, Student.class);
        return this.studentRepository.save(student);
    }



    @Override
    public Student getStudent(String studentRegNo) throws EntityNotFoundException {
        Student student = this.studentRepository.findByStudentRegistrationNo(studentRegNo);
        if (student != null) {
            return student;
        }
        throw new EntityNotFoundException(Student.class, "StudentRegistrationNo", studentRegNo.toString());
    }

    @Override
    public Student getStudent(UUID rrn) throws EntityNotFoundException {
        Student student = this.studentRepository.findByRrn(rrn.toString());
        if (student != null) {
            return student;
        }
        throw new EntityNotFoundException(Student.class, "Retrieval Reference Number", rrn.toString());
    }

    @Override
    public Student update(StudentDto studentDto) throws EntityNotFoundException {
        Optional<Student> studentInDb = Optional.ofNullable(this.studentRepository.findByRrn(studentDto.getRrn().toString()));
        if (!studentInDb.isPresent()) {
            throw new EntityNotFoundException(Student.class, "Retrieval Reference Number", studentDto.getRrn().toString());
        }


        Student student = mapper.map(studentDto, Student.class);
        student.setId(studentInDb.get().getId());
        return this.studentRepository.save(student);
    }

    @Override
    public int deleteStudent(Long studentId) {
        this.studentRepository.deleteById(studentId);
        return 1;
    }

    @Override
    public List<Student> filterStudentByCourse(String courseCode) {
        Course course=this.courseRepository.findByCourseCode(courseCode);
        Set<Course> courseSet=new HashSet();
        if(course!=null){
            courseSet.add(course);
            return this.studentRepository.findByOfferedCoursesIn(course.getId());
        }
        return null;
    }

    @Override
    public List<Student> filterStudentByCourse() {

        return this.studentRepository.findByOfferedCoursesNotIn();
    }


}
