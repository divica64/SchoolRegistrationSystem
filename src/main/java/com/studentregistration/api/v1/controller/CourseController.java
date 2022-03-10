package com.studentregistration.api.v1.controller;

import com.studentregistration.api.v1.request.course.CreateCourseRequest;
import com.studentregistration.api.v1.request.course.UpdateCourseRequest;
import com.studentregistration.domain.model.Course;
import com.studentregistration.dto.CourseDto;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;
import com.studentregistration.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/v1/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    private ModelMapper modelMapper=new ModelMapper();

    @GetMapping(value = {"/filter-by/student/{studentRegistrationNo}"})
    public List<CourseDto> filter(@PathVariable(value = "studentRegistrationNo", name="studentRegistrationNo", required = false) String studentRegistrationNo ) {

        List<Course> courses = this.courseService.filterByStudents(studentRegistrationNo);
        if (courses != null && !courses.isEmpty()) {
            return courses
                    .stream()
                    .map(course -> modelMapper.map(course, CourseDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    @GetMapping(value = {"/filter-by/student"})
    public List<CourseDto> filter() {
        List<Course> courses = this.courseService.filterByStudents("");
        if (courses != null && !courses.isEmpty()) {
            return courses
                    .stream()
                    .map(course -> modelMapper.map(course, CourseDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

   @GetMapping(value = "/{rrn}")
    public CourseDto get(@PathVariable("rrn") UUID rrn) throws EntityNotFoundException {
        log.info(rrn.toString());
        Course course = this.courseService.getCourse(rrn);
        if (course != null) {
            return modelMapper.map(course, CourseDto.class);
        }
        return null;
    }

    @PostMapping("/")
    public CourseDto create(@RequestBody @Valid CreateCourseRequest createCourseRequest) throws EntityException {
        CourseDto courseDto = modelMapper.map(createCourseRequest, CourseDto.class);
        Course course = this.courseService.createCourse(courseDto);
        if (course != null) {
            return modelMapper.map(course, CourseDto.class);
        }
        return null;
    }

    @DeleteMapping("/{rrn}")
    public ResponseEntity<String> delete(@PathVariable("rrn") UUID rrn) throws EntityException {
        Course course = this.courseService.getCourse(rrn);
        if (course != null) {
            this.courseService.deleteCourse(course.getId());
            return new ResponseEntity<String>(String.format("Course with CourseCode=%s deleted successfully!",rrn), HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("/")
    public CourseDto update(@RequestBody @Valid UpdateCourseRequest updateCourseRequest) throws EntityException {


        Course course = this.courseService.getCourse(updateCourseRequest.getRrn());
        System.out.println(updateCourseRequest.getRrn());

        if (course != null) {
            CourseDto courseDto = modelMapper.map(updateCourseRequest, CourseDto.class);
            course = this.courseService.updateCourse(courseDto);
            return modelMapper.map(course, CourseDto.class);
        }
        return null;
    }
}
