package com.studentregistration.domain.repository;

import com.studentregistration.domain.model.Course;
import com.studentregistration.domain.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByStudentRegistrationNo(String regNo);
    //Student findByRrn(UUID rrn);
    Student findByRrn(String rrn);

    List<Student> findByOfferedCoursesIn(Set<Course> course);
    @Query(value = "SELECT * FROM student s  WHERE s.id IN (SELECT s.students_id FROM student_offered_courses s WHERE s.offered_courses_id=?)",nativeQuery = true)
    List<Student> findByOfferedCoursesIn(long courseId);

    @Query(value = "SELECT * FROM student s  WHERE s.id NOT IN (SELECT s.students_id FROM student_offered_courses s)",nativeQuery = true)
    List<Student> findByOfferedCoursesNotIn();


}
