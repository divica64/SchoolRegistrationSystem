package com.studentregistration.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author Oluwafemi Fagbemi.
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Student extends BaseDomain {



    @Column(unique = true)
    private String studentRegistrationNo;
    private String firstname;
    private String lastname;
    private String dept;
    private String phone;
    private String email;
    @ManyToMany
    @JoinColumn(name = "studentId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Course> offeredCourses;
}
