package com.studentregistration.service.registration;

import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.CourseRegistrationRequestDto;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.exception.CourseLimitExceededException;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * @author Oluwafemi Fagbemi
 */
public interface StudentCourseRegistrationService {

    Student registerStudentCourse(CourseRegistrationRequestDto courseRegistrationRequestDto) throws EntityException, CourseLimitExceededException;

}
