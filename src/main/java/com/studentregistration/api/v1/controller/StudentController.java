package com.studentregistration.api.v1.controller;

import com.studentregistration.api.v1.request.student.CreateStudentRequest;
import com.studentregistration.api.v1.request.student.UpdateStudentRequest;
import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.CourseDto;
import com.studentregistration.dto.FilterStudentDto;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.exception.EntityException;
import com.studentregistration.exception.EntityNotFoundException;
import com.studentregistration.service.student.StudentService;
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
@RequestMapping("/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    private ModelMapper modelMapper=new ModelMapper();


    //Filter all students without any course
    @GetMapping(value = "/filter-by/course")
    public List<FilterStudentDto> filter() {
        List<Student> students = this.studentService.filterStudentByCourse();
        if (students != null && !students.isEmpty()) {
            return students
                    .stream()
                    .map(student -> modelMapper.map(student, FilterStudentDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    //Filter all students with a specific course
    @GetMapping(value = "/filter-by/course/{courseCode}")
    public List<FilterStudentDto> filter(@PathVariable("courseCode") String courseCode) {
        List<Student> students = this.studentService.filterStudentByCourse(courseCode);
        if (students != null && !students.isEmpty()) {
            return students
                    .stream()
                    .map(student -> modelMapper.map(student, FilterStudentDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();


    }

    @GetMapping(value = "/{rrn}")
    public StudentDto get(@PathVariable("rrn") UUID rrn) throws EntityNotFoundException {
        Student student = this.studentService.getStudent(rrn);
        if (student != null) {
            return modelMapper.map(student, StudentDto.class);
        }
        return null;
    }

    @PostMapping("/")
    public StudentDto create(@RequestBody @Valid CreateStudentRequest createStudentRequest) throws EntityException {


        StudentDto studentDto = modelMapper.map(createStudentRequest, StudentDto.class);
        Student student = this.studentService.save(studentDto);
        if (student != null) {
            return modelMapper.map(student, StudentDto.class);
        }
        return null;
    }

    @PutMapping("/")
    public StudentDto update(@RequestBody @Valid UpdateStudentRequest updateStudentRequest) throws EntityException {

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Student student = this.studentService.getStudent(UUID.fromString(updateStudentRequest.getRrn()));
        if (student != null) {
            StudentDto studentDto = modelMapper.map(updateStudentRequest, StudentDto.class);
            student = this.studentService.update(studentDto);
            return modelMapper.map(student, StudentDto.class);
        }
        return null;
    }

    @DeleteMapping("/{rrn}")
    public ResponseEntity<String> delete(@PathVariable("rrn") UUID rrn) throws EntityException {
        Student student = this.studentService.getStudent(rrn);
        if (student != null) {
            //return mapper.map(student, StudentDto.class);
            this.studentService.deleteStudent(student.getId());
            return new ResponseEntity<String>(String.format("Student with Retrieval Reference Number=%s deleted successfully!", rrn), HttpStatus.OK);
        }
        return null;
    }
}
