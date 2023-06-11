package com.alkemy.gestoralumnos.repository;

import com.alkemy.gestoralumnos.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT AVG(s.age) FROM CourseRegistration cr JOIN cr.student s WHERE cr.course.id = :courseId")
    double getAverageAgeByCourseId(Long courseId);

    @Query("SELECT MAX(s.entranceGrade) FROM CourseRegistration cr JOIN cr.student s WHERE cr.course.id = :courseId")
    double findHighestEntranceGradeByCourseId(Long courseId);
}
