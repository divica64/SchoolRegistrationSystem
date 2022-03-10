package com.studentregistration.exception;

import com.studentregistration.domain.model.Course;

import java.util.Set;

public class StudentLimitExceededException extends Exception{

    public StudentLimitExceededException(String message){
        super(message);
    }
    public StudentLimitExceededException(){
        super("Student Course Enrolment Limit Exceeded");
    }


}
