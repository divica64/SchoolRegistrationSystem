package com.studentregistration.api.v1.request.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UpdateCourseRequest {




    private UUID rrn;
    @NotNull
    @NotEmpty
    private String courseName;
    @NotNull
    @NotEmpty
    @Size(min=6,max=6,message="courseCode must be 6 characters")
    private String courseCode;

}
