package com.studentregistration.dto;

import com.studentregistration.domain.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
public class StudentDto {


    private String rrn;
    private String studentRegistrationNo;
    private String dept;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private List<CourseDto> courses=new ArrayList();


}
