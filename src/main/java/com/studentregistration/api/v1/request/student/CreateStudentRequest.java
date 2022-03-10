package com.studentregistration.api.v1.request.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStudentRequest {
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String firstname;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private String lastname;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private String studentRegistrationNo;

    private String dept;
    private String email;
    private String phone;
}
