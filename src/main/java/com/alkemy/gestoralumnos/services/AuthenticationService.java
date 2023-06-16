package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.JwtAuthenticationResponse;
import com.alkemy.gestoralumnos.dto.SignInRequest;
import com.alkemy.gestoralumnos.dto.SignUpRequest;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var student = Student.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .dni(request.getDni())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        studentRepository.save(student);
        var jwt = jwtService.generateToken(student);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public  JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(student);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
