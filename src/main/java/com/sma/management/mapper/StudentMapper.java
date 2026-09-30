package com.sma.management.mapper;

import com.sma.management.dto.StudentResponseDto;
import com.sma.management.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentResponseDto mapToResponseDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }
}
