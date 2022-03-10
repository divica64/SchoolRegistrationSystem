package com.studentregistration.api.v1.request.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseRegistrationDto {
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String studentRegistrationNo;

    @NotNull(message = "{constraints.NotEmpty.message}")
    @Size(min=1,max=5)
   private Set<String> courseCodes;
}
