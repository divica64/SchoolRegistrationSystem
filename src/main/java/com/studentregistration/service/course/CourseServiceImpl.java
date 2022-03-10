package com.studentregistration.service.course;


import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.domain.repository.CourseRepository;
import com.studentregistration.domain.repository.StudentRepository;
import com.studentregistration.dto.CourseDto;
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
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    private ModelMapper mapper=new ModelMapper();

    @Override
    public Course createCourse(CourseDto courseDto) throws EntityException {
        Optional<Course> courseInDb = Optional.ofNullable(this.courseRepository.findByCourseCode(courseDto.getCourseCode()));
        if (courseInDb.isPresent()) {
            throw new DuplicateEntityException(Course.class, "CourseCode", courseDto.getCourseCode().toString());
        }
        Course course = mapper.map(courseDto, Course.class);
        return this.courseRepository.save(course);
    }

    @Override
    public Course getCourse(String courseCode) throws EntityNotFoundException {
        Course course = this.courseRepository.findByCourseCode(courseCode);
        if (course != null) {
            return course;
        }
        throw new EntityNotFoundException(Course.class, "Course Code", courseCode);

    }

    @Override
    public Course getCourse(UUID rrn) throws EntityNotFoundException {
        Course course = this.courseRepository.findByRrn(rrn.toString());
        if (course != null) {
            return course;
        }
        throw new EntityNotFoundException(Course.class, "Retrieval Reference Number", rrn.toString());
    }

    @Override
    public int deleteCourse(Long courseId) {
        this.courseRepository.deleteById(courseId);
        return 1;
    }

    @Override
    public Course updateCourse(CourseDto courseDto) throws EntityNotFoundException {
        Optional<Course> courseInDb = Optional.ofNullable(this.courseRepository.findByRrn(courseDto.getRrn().toString()));
        if (!courseInDb.isPresent()) {
            throw new EntityNotFoundException(Course.class, "Retrieval Reference Number", courseDto.getRrn().toString());
        }
        Course course = mapper.map(courseDto, Course.class);
        course.setId(courseInDb.get().getId());
        return this.courseRepository.save(course);
    }



    @Override
    public List<Course> filterByStudents(String regNo) {

        Student student = this.studentRepository.findByStudentRegistrationNo(regNo);
        Set<Student> studentSet = new HashSet();
        if (!regNo.isEmpty()) {
            if (student != null) {
                studentSet.add(student);
                return this.courseRepository.findByStudentsIn(studentSet);

            }
            return null;
        } else {
            return this.courseRepository.findByStudentsNotIn();
        }
    }

    @Override
    public List<Course> filterByCourseCodeIn(Set<String> courseCodes) {

        Iterator<Course> iteratorToCollection = this.courseRepository.findByCourseCodeIn(courseCodes).stream().iterator();
        return StreamSupport.stream(
                        Spliterators
                                .spliteratorUnknownSize(iteratorToCollection, Spliterator.ORDERED), false)
                .collect(Collectors.toList()
                );
    }
}
