package com.alkemy.gestoralumnos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name="students")
@Entity(name="Student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Student implements UserDetails {
    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @NotNull
    private String dni;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int age;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;
    @OneToMany(mappedBy = "student")
    List<CourseRegistration> registrations;

    public Student(String name, String surname, int age, String dni, String email, String password, boolean hasSubjectDebts, boolean hasEnrollmentDebt, double entranceGrade) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.role = Role.STUDENT;
        this.age = age;
        this.hasSubjectDebts = hasSubjectDebts;
        this.hasEnrollmentDebt = hasEnrollmentDebt;
        this.entranceGrade = entranceGrade;
        registrations = new ArrayList<>();
    }

    public void setRegistration(CourseRegistration registration) {
        registration.setStudent(this);
        registrations.add(registration);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
