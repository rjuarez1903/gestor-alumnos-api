package com.alkemy.gestoralumnos.exceptions;

import com.alkemy.gestoralumnos.exceptions.courseExceptions.CourseNotFoundException;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.NoEnrollmentsException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentAlreadyEnrolledException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> StudentNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyEnrolledException.class)
    public ResponseEntity<?> StudentAlreadyEnrolledException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> CourseNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoEnrollmentsException.class)
    public ResponseEntity<?> NoEnrollmentsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
