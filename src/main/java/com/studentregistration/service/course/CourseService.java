package com.studentregistration.service.course;

import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.CourseDto;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Oluwafemi Fagbemi
 */
public interface CourseService {
    Course createCourse(CourseDto courseDto) throws EntityException;
    Course getCourse(String courseCode) throws EntityNotFoundException;
    Course getCourse(UUID rrn) throws EntityNotFoundException;
    int deleteCourse(Long courseId);
    Course updateCourse(CourseDto courseDto) throws EntityNotFoundException;
    List<Course> filterByStudents(String regNo);
    List<Course> filterByCourseCodeIn(Set<String> courseCodes);

}
