package com.sma.management.controller;

import com.sma.management.dto.StudentRequestDto;
import com.sma.management.dto.StudentResponseDto;
import com.sma.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto requestDto) {

        StudentResponseDto responseDto = studentService.createStudent(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudent() {
        List<StudentResponseDto> responceDtoList = studentService.getAllStudent();

        return ResponseEntity.status(HttpStatus.OK).body(responceDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto responseDto = studentService.getStudentById(id);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
                                                            @Valid @RequestBody StudentRequestDto requestDto) {
        StudentResponseDto updatedStudent = studentService.updateStudent(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDto> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
