package com.sma.management.service;

import com.sma.management.dto.StudentRequestDto;
import com.sma.management.dto.StudentResponseDto;
import com.sma.management.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    List<StudentResponseDto> getAllStudent();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);

    void deleteStudentById(Long id);
}
