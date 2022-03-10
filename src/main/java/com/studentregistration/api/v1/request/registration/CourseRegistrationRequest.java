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
public class CourseRegistrationRequest {
    @NotNull
    private String studentRegistrationNo;

    @NotNull
    @Size(min=1,max=5, message ="{crs.limit.exceeded}")
   private Set<String> courseCodes;
}
