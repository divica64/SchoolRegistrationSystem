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
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findByCourseCode(String courseCode);
   // Course findByRrn(UUID rrn);
    Course findByRrn(String rrn);

    Set<Course> findByCourseCodeIn(Set<String> courseCodes);
    List<Course> findByStudentsIn(Set<Student> students);
    @Query(value = "SELECT * FROM course c  WHERE c.id NOT IN (SELECT s.offered_courses_id FROM student_offered_courses s)",nativeQuery = true)
    List<Course> findByStudentsNotIn();


}
