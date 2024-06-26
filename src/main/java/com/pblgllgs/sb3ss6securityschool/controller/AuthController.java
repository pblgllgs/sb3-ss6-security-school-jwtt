package com.pblgllgs.sb3ss6securityschool.controller;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import com.pblgllgs.sb3ss6securityschool.dto.*;
import com.pblgllgs.sb3ss6securityschool.entity.AdminEntity;
import com.pblgllgs.sb3ss6securityschool.entity.StudentEntity;
import com.pblgllgs.sb3ss6securityschool.entity.TeacherEntity;
import com.pblgllgs.sb3ss6securityschool.enums.UserType;
import com.pblgllgs.sb3ss6securityschool.repository.AdminRepo;
import com.pblgllgs.sb3ss6securityschool.repository.StudentRepo;
import com.pblgllgs.sb3ss6securityschool.repository.TeacherRepo;
import com.pblgllgs.sb3ss6securityschool.service.CustomUserDetailsService;
import com.pblgllgs.sb3ss6securityschool.util.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/api/v1/adminRegister")
    public ResponseEntity<String> adminRegister(@RequestBody AdminAuthDto adminAuthDto) {
        if(adminRepo.existsByUsername(adminAuthDto.getUsername())) {
            return new ResponseEntity<String>("Username is taken !! ", HttpStatus.BAD_REQUEST);
        }
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(adminAuthDto.getUsername());
        adminEntity.setPassword(passwordEncoder.encode(adminAuthDto.getPassword()));

        adminRepo.save(adminEntity);
        return new ResponseEntity<String>("User Register successfull !! ", HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/adminLogin")
    public ResponseEntity<AdminLoginResponseDto> login(@RequestBody AdminAuthDto adminAuthDto) {
        System.out.println("adminLogin");
        customUserDetailsService.setUserType(UserType.ADMIN);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminAuthDto.getUsername(), adminAuthDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication,UserType.ADMIN.toString());
        AdminLoginResponseDto responseDto = new AdminLoginResponseDto();
        responseDto.setSuccess(true);
        responseDto.setMessage("login successful !!");
        responseDto.setToken(token);
        AdminEntity admin = adminRepo.findByUsername(adminAuthDto.getUsername()).orElseThrow();
        responseDto.setAdmin(admin.getUsername(), admin.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/api/v1/teacherLogin")
    public ResponseEntity<TeacherLoginResponseDto> teacherLogin(@RequestBody TeacherLoginDto teacherLoginDto) {
        System.out.println("teacherLogin");
        customUserDetailsService.setUserType(UserType.TEACHER);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(teacherLoginDto.getEmail(), teacherLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication, UserType.TEACHER.toString());
        TeacherLoginResponseDto responseDto = new TeacherLoginResponseDto();
        responseDto.setSuccess(true);
        responseDto.setMessage("login successful !!");
        responseDto.setToken(token);
        TeacherEntity teacher = teacherRepo.findByEmail(teacherLoginDto.getEmail()).orElseThrow();
        responseDto.setTeacher(teacher.getName(), teacher.getEmail(), teacher.getId());
        return new ResponseEntity<TeacherLoginResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/api/v1/studentRegister")
    public ResponseEntity<SuccessandMessageDto> studentRegister(@RequestBody StudentRegisterDto studentRegisterDto) {
        System.out.println("studentRegister");
        SuccessandMessageDto response = new SuccessandMessageDto();
        if(studentRepo.existsByEmail(studentRegisterDto.getEmail())) {
            response.setMessage("Email is already registered !!");
            response.setSuccess(false);
            return new ResponseEntity<SuccessandMessageDto>(response, HttpStatus.BAD_REQUEST);
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentRegisterDto.getUsername());
        studentEntity.setPassword(passwordEncoder.encode(studentRegisterDto.getPassword()));
        studentEntity.setEmail(studentRegisterDto.getEmail());
        studentEntity.setStatus(true);
        studentRepo.save(studentEntity);
        response.setMessage("Profile Created Successfully !!");
        response.setSuccess(true);
        return new ResponseEntity<SuccessandMessageDto>(response, HttpStatus.OK);
    }

    @PostMapping("/api/v1/studentLogin")
    public ResponseEntity<StudentLoginResponseDto> studentLogin(@RequestBody StudentLoginDto studentLoginDto) {
        System.out.println("studentLogin");
        customUserDetailsService.setUserType(UserType.STUDENT);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(studentLoginDto.getEmail(), studentLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication, UserType.STUDENT.toString());
        StudentLoginResponseDto responseDto = new StudentLoginResponseDto();
        responseDto.setSuccess(true);
        responseDto.setMessage("login successful !!");
        responseDto.setToken(token);
        StudentEntity student = studentRepo.findByEmail(studentLoginDto.getEmail()).orElseThrow();
        responseDto.setStudent(student.getName(), student.getEmail(), student.getId());
        return new ResponseEntity<StudentLoginResponseDto>(responseDto, HttpStatus.OK);
    }
}
