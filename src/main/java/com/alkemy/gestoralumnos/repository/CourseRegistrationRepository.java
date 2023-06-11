package com.alkemy.gestoralumnos.repository;

import com.alkemy.gestoralumnos.models.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

}
