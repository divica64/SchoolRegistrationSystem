package com.studentregistration.unittest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentregistration.api.v1.controller.CourseController;
import com.studentregistration.api.v1.controller.CourseRegistrationController;
import com.studentregistration.api.v1.controller.StudentController;
import com.studentregistration.api.v1.request.student.CreateStudentRequest;
import com.studentregistration.api.v1.request.student.UpdateStudentRequest;
import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.CourseRegistrationRequestDto;
import com.studentregistration.dto.StudentDto;
import com.studentregistration.service.course.CourseService;
import com.studentregistration.service.registration.StudentCourseRegistrationService;
import com.studentregistration.service.registration.StudentCourseRegistrationServiceImpl;
import com.studentregistration.service.student.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseRegistrationController.class)
public class CourseRegistrationControllerTests {


    @MockBean
    private StudentCourseRegistrationServiceImpl studentCourseRegistrationService;

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;







    @Test
    public void courseRegisrationUnitTest() throws Exception {


        CourseRegistrationRequestDto crsReg=new CourseRegistrationRequestDto();
        crsReg.setStudentRegistrationNo("ECO202201");
        Course crs=new Course();
        crsReg.setCourseCodes(
                new HashSet<String>(
                        Arrays.asList("CHM102","MTH103")
                )
        );

        Student student = new Student();
        student.setFirstname("Aril");
        student.setLastname("Jugul");
        student.setEmail("Aril@yahoo.com");
        student.setStudentRegistrationNo("ECO202201");
        student.setPhone("23490453535353535");
        student.setDept("Economics");
        student.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");

        Course aCourse=new Course();
        aCourse.setCourseCode("CHM102");
        aCourse.setRrn("419c7bd1-9ebb-11ec-8e9e-20c9eaec064c");
        aCourse.setCourseName("Applied Chemistry");

        Course anotherCourse=new Course();
        anotherCourse.setCourseCode("MAT103");
        anotherCourse.setRrn("419c7bd1-9ebb-11ec-8e9e-30c9eaec064c");
        anotherCourse.setCourseName("Calculus");

        student.setOfferedCourses(new HashSet<>(
                Arrays.asList(
                        aCourse,anotherCourse
                )
        ));
        student.setId(1L);



       Mockito.when(
                studentCourseRegistrationService.registerStudentCourse(
                        Mockito.any(CourseRegistrationRequestDto.class)))
                .thenReturn(student);

        Mockito.when(
                        courseService.filterByCourseCodeIn(
                                Mockito.any(HashSet.class)))
                .thenReturn(new ArrayList<Course>());

        ObjectMapper objectMapper=new ObjectMapper();
        String mockRequest=objectMapper.writeValueAsString(crsReg);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/course/registration")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE);



        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseCodes").isArray())
                .andExpect(jsonPath("$.studentRegistrationNo").value("ECO202201"))
                .andDo(print());



    }




}
