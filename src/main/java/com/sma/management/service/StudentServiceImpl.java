package com.sma.management.service;

import com.sma.management.dto.StudentRequestDto;
import com.sma.management.dto.StudentResponseDto;
import com.sma.management.entity.Student;
import com.sma.management.mapper.StudentMapper;
import com.sma.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {

        if (studentRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        Student student = new Student();

        student.setName(requestDto.getName());
        student.setEmail(requestDto.getEmail());
        student.setPassword(requestDto.getPassword());

        Student studentCreated = studentRepository.save(student);

        return studentMapper.mapToResponseDto(studentCreated);
    }

    @Override
    public List<StudentResponseDto> getAllStudent() {

        List<Student> students = studentRepository.findAll();
        List<StudentResponseDto> responseDtoList = new ArrayList<>();

        for (Student student : students) {
            responseDtoList.add(
                    studentMapper.mapToResponseDto(student)
            );
        }

        return responseDtoList;
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + id
                        ));

        return studentMapper.mapToResponseDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(
            Long id,
            StudentRequestDto studentRequestDto) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with ID: " + id
                        ));

        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setPassword(studentRequestDto.getPassword());

        Student studentUpdated = studentRepository.save(student);

        return studentMapper.mapToResponseDto(studentUpdated);
    }

    @Override
    public void deleteStudentById(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Student not found with ID: " + id
            );
        }

        studentRepository.deleteById(id);
    }
}