package com.example.demo.Student;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        boolean studentExist= studentRepository.existsById(id);
        if(!studentExist){
            throw new IllegalStateException("student with id "+id+" does not exist");
        }
        studentRepository.deleteById(id);
    }

     @Transactional
    public void updateStudent(Long id, String name, String email){
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id" + id + "does not exist"));
        if(name!= null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);

        }

         if(email  != null && !Objects.equals(student.getEmail(), email)){
                 student.setEmail(email);

         }

    }
}
