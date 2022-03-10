package com.studentregistration.exception;

import com.studentregistration.domain.model.Course;

import java.util.Set;

public class CourseLimitExceededException extends Exception{

    private Set<Course> courses;
    public CourseLimitExceededException (String message, Set<Course> crs){
        super(message);
        this.courses=crs;
    }
    public CourseLimitExceededException (){
        super("Student can NOT register for more than 5 courses");
    }


}
