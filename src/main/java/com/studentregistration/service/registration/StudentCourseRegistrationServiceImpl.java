package com.studentregistration.service.registration;


import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.domain.repository.StudentRepository;
import com.studentregistration.dto.CourseRegistrationRequestDto;
import com.studentregistration.exception.*;
import com.studentregistration.service.course.CourseService;
import com.studentregistration.service.student.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oluwafemi Fagbemi
 */
@Service
@PropertySource("messages_en.properties")
@Slf4j
public class StudentCourseRegistrationServiceImpl implements StudentCourseRegistrationService {


    @Value( "${crs.limit.exceeded}")
    private String courseLimitExceded;

    @Value( "${no.of.crs.allowed}")
    private int MAX_CRS_ALLOWED;

    @Value( "${student.crs.enrolment.limit}")
    private int STUDENT_COURSE_ENROLMENT_LIMIT;

    @Value( "${student.crs.enrolment.limit.exceeded}")
    private String STUDENT_COURSE_ENROLMENT_LIMIT_EXCEEDED;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    private ModelMapper mapper=new ModelMapper();

    @Autowired
    private StudentRepository studentRepository;
    public Student registerStudentCourse(CourseRegistrationRequestDto courseRegistrationRequestDto) throws EntityException, CourseLimitExceededException {



        Student student=this.studentService.getStudent(courseRegistrationRequestDto.getStudentRegistrationNo());
        List<Course> courses=this.courseService.filterByCourseCodeIn(courseRegistrationRequestDto.getCourseCodes());

        //Check Course Enrolment
        student.getOfferedCourses()
                        .stream()
                .forEach(cc->{System.out.println(cc.getCourseCode()+"----"+cc.getStudents().size());});


        student.getOfferedCourses()
                .stream()
                        .forEach(course->{
                            System.out.println(course.getStudents().size());
                            System.out.println(STUDENT_COURSE_ENROLMENT_LIMIT);

                            if(course.getCourseCode().equals(courseRegistrationRequestDto.getCourseCodes())
                            && course.getStudents().size()>=STUDENT_COURSE_ENROLMENT_LIMIT-1)
                            {
                                try {
                                    throw new StudentLimitExceededException(
                                            String.format(STUDENT_COURSE_ENROLMENT_LIMIT_EXCEEDED,STUDENT_COURSE_ENROLMENT_LIMIT+"",course.getCourseCode())
                                    );
                                } catch (StudentLimitExceededException e) {
                                    System.out.println(e.getMessage());
                                    throw new RuntimeException(e);
                                }
                            }
                        });

        student.getOfferedCourses().addAll(courses);
        Set<Course> setCrxs=new HashSet<Course>();
        setCrxs.addAll(student.getOfferedCourses());

        if(setCrxs.size()>MAX_CRS_ALLOWED)
        {
            List<String> cc=student.getOfferedCourses().stream().map(crs->crs.getCourseCode())
                    .collect(Collectors.toList());
           throw new CourseLimitExceededException(courseLimitExceded.concat(":").concat(cc.toString()),
                   student.getOfferedCourses());
        }

        student.setOfferedCourses(setCrxs);
        System.out.println("=======================XXXXXXXXXXXXXXXXXXXXXXX==========================="+courses.size());
        return this.studentRepository.save(student);
    }




}
