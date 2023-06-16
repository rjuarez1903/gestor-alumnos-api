package com.alkemy.gestoralumnos.repository;

import com.alkemy.gestoralumnos.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    void findByDni(String email);
}
