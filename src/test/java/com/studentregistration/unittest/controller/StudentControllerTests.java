package com.studentregistration.unittest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentregistration.api.v1.controller.StudentController;
import com.studentregistration.api.v1.request.student.CreateStudentRequest;
import com.studentregistration.api.v1.request.student.UpdateStudentRequest;
import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import com.studentregistration.dto.StudentDto;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTests {


    @MockBean
    private StudentServiceImpl studentService;

    @Autowired
    private MockMvc mockMvc;







    @Test
    public void createStudentUnitTest() throws Exception {


        CreateStudentRequest csr=new CreateStudentRequest();
        csr.setFirstname("Aril");
        csr.setLastname("Jugul");
        csr.setEmail("Aril@yahoo.com");
        csr.setStudentRegistrationNo("MTH001");
        csr.setPhone("23490453535353535");
        csr.setDept("Economics");

        ModelMapper mapper=new ModelMapper();
        StudentDto studentDto=mapper.map(csr,StudentDto.class);
        Student mockStudent=mapper.map(csr, Student.class);
        mockStudent.setId(1L);
        mockStudent.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");

       Mockito.when(
                studentService.save(
                        Mockito.any(StudentDto.class)))
                .thenReturn(mockStudent);

        //studentService.save(studentDto);
        // given
        // Send course as body to /students/Student1/courses

        ObjectMapper objectMapper=new ObjectMapper();
        String mockRequest=objectMapper.writeValueAsString(csr);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/student/")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).
                andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
System.out.println("----"+ response.getContentAsString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void getStudentUnitTest() throws Exception {

        String mockStudentRegNo="6a2f41a3-c54c-fce8-32d2-0324e1c32e22";
        Student student = new Student();
        student.setFirstname("Aril");
        student.setLastname("Jugul");
        student.setEmail("Aril@yahoo.com");
        student.setStudentRegistrationNo("MTH001");
        student.setPhone("23490453535353535");
        student.setDept("Economics");
        student.setRrn(mockStudentRegNo);
        student.setId(1L);

        student.setOfferedCourses(new HashSet<Course>());
        Mockito.when(
                studentService.getStudent(Mockito.any(UUID.class)))
                .thenReturn(student);
        MvcResult result=mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/v1/student/6a2f41a3-c54c-fce8-32d2-0324e1c32e22")
                                .accept(MediaType.ALL_VALUE)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        System.out.println("Res"+response.getContentAsString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        //assertEquals(mockStudentRegNo,response.getCo)

              //  .andExpect(status().isOk())
              //  .andExpect(jsonPath("rrn", Matchers.equalTo("6a2f41a3-c54c-fce8-32d2-0324e1c32e22")));
    }

    @Test
    public void updateStudentUnitTest() throws Exception{

        UpdateStudentRequest csr=new UpdateStudentRequest();
        csr.setFirstname("Aril");
        csr.setLastname("Jugul");
        csr.setEmail("Aril@yahoo.com");
        csr.setStudentRegistrationNo("MTH001");
        csr.setPhone("23490453535353535");
        csr.setDept("Economics");
        csr.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");


        Student student = new Student();
        student.setFirstname("Aril");
        student.setLastname("Jugul");
        student.setEmail("Aril@yahoo.com");
        student.setStudentRegistrationNo("MTH001");
        student.setPhone("23490453535353535");
        student.setDept("Economics");
        student.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");
        student.setId(1L);


        Mockito.when(
                        studentService.getStudent(
                                Mockito.anyString()))
                .thenReturn(student);

        Mockito.when(
                        studentService.update(
                                Mockito.any(StudentDto.class)))
                .thenReturn(student);

        ObjectMapper objectMapper=new ObjectMapper();

        String mockRequest=objectMapper.writeValueAsString(csr);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/v1/student/")
                .accept(MediaType.ALL_VALUE)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                       .andExpect(jsonPath("$.rrn").value("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c"))
                .andDo(print());


    }

    @Test
    public void deleteStudentUnitTest() throws Exception{


        Student student = new Student();
        student.setFirstname("Aril");
        student.setLastname("Jugul");
        student.setEmail("Aril@yahoo.com");
        student.setStudentRegistrationNo("MTH001");
        student.setPhone("23490453535353535");
        student.setDept("Economics");
        student.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");
        student.setId(1L);


        Mockito.when(
                        studentService.getStudent(
                                Mockito.any(UUID.class)))
                .thenReturn(student);

        Mockito.when(
                        studentService.deleteStudent(
                                Mockito.any(Long.class)))
                .thenReturn(1);

        ObjectMapper objectMapper=new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/v1/student/419c7bd1-9ebb-11ec-8e9e-17c9eaec064c")
                .accept(MediaType.ALL_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("deleted successfully")));



    }

    @Test
    public void filterStudent_ThoseWhoAreNotRegisteredToAnyCourseUnitTestz() throws Exception {

        List<Student> students=new ArrayList();


        Student student = new Student();
        student.setFirstname("Aril");
        student.setLastname("Jugul");
        student.setEmail("Aril@yahoo.com");
        student.setStudentRegistrationNo("MTH001");
        student.setPhone("23490453535353535");
        student.setDept("Economics");
        student.setRrn("419c7bd1-9ebb-11ec-8e9e-17c9eaec064c");
        student.setId(1L);
        students.add(student);

        student = new Student();
        student.setFirstname("Jay");
        student.setLastname("Amani");
        student.setEmail("amani@yahoo.com");
        student.setStudentRegistrationNo("MTH008");
        student.setPhone("2349022353535");
        student.setDept("Chemistry");
        student.setRrn("679c7bd1-9ebb-11ec-1c1c-17c9eaec064c");
        student.setId(2L);
        students.add(student);




        Mockito.when(
                        studentService.filterStudentByCourse())
                .thenReturn(students);



        ObjectMapper objectMapper=new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/student/filter-by/course")
                .accept(MediaType.ALL_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());


    }


}
