package com.studentregistration.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@Setter
@Getter
public class CourseDto {


    private String rrn;
    @NotNull
    @NotEmpty(message="{not.empty}")
    private String courseName;
    @NotNull
    @NotEmpty(message="{not.empty}")
    @Size(min = 6,max=6,message = "{0} must be 6 characters")
    private String courseCode;
}
