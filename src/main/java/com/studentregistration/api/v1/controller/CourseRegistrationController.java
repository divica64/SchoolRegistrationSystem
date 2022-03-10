package com.studentregistration.api.v1.controller;

import com.studentregistration.api.v1.request.course.CreateCourseRequest;
import com.studentregistration.api.v1.request.registration.CourseRegistrationDto;
import com.studentregistration.api.v1.request.registration.CourseRegistrationRequest;
import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.CourseDto;
import com.studentregistration.dto.CourseRegistrationRequestDto;
import com.studentregistration.exception.CourseLimitExceededException;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;
import com.studentregistration.service.registration.StudentCourseRegistrationService;
import com.studentregistration.service.student.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CourseRegistrationController {


    @Autowired
    private StudentCourseRegistrationService studentCourseRegistrationService;
    private ModelMapper modelMapper=new ModelMapper();


    @PostMapping(value = "/v1/course/registration")
    public CourseRegistrationDto create(@RequestBody @Valid CourseRegistrationRequest courseRegistrationRequest) throws EntityException, CourseLimitExceededException {

        CourseRegistrationRequestDto crsDto=this.modelMapper.map(courseRegistrationRequest, CourseRegistrationRequestDto.class);
        Student student=this.studentCourseRegistrationService.registerStudentCourse(crsDto);
        CourseRegistrationDto courseRegistrationDto= this.modelMapper.map(student,CourseRegistrationDto.class);
        List<String> crs=student.getOfferedCourses()
                .stream().map(course->course.getCourseCode())
                .collect(Collectors.toList());
        courseRegistrationDto.setCourseCodes(new HashSet(crs));
        return courseRegistrationDto;
    }
}
