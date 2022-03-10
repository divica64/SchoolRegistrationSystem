package com.studentregistration.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class Course extends BaseDomain {


    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID courseId=UUID.randomUUID();
*/





/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;
*/
    private String courseName;
    @Column(unique = true)
    private String courseCode;
    @ManyToMany(mappedBy = "offeredCourses",fetch = FetchType.LAZY)
    Set<Student> students;




}
