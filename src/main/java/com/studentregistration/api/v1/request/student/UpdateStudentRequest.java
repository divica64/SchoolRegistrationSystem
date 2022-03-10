package com.studentregistration.api.v1.request.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStudentRequest {

    @NotEmpty
    @NotNull
    private String rrn;
    @NotNull(message = "{not.empty}")
    private String firstname;

    @NotNull(message = "{not.empty}")
    private String lastname;

    @NotNull(message = "{not.empty}")
    private String studentRegistrationNo;

    @NotEmpty
    @NotNull
    private String dept;
    private String email;
    private String phone;
}
