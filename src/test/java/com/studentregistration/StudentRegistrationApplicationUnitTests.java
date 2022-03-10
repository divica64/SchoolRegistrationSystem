package com.studentregistration;


import com.studentregistration.unittest.controller.CourseRegistrationControllerTests;
import com.studentregistration.unittest.controller.StudentControllerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CourseRegistrationControllerTests.class,
		StudentControllerTests.class
})
public class StudentRegistrationApplicationUnitTests {



}
