package com.pblgllgs.sb3ss6securityschool.service;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import com.pblgllgs.sb3ss6securityschool.entity.AdminEntity;
import com.pblgllgs.sb3ss6securityschool.entity.StudentEntity;
import com.pblgllgs.sb3ss6securityschool.entity.TeacherEntity;
import com.pblgllgs.sb3ss6securityschool.enums.UserType;
import com.pblgllgs.sb3ss6securityschool.repository.AdminRepo;
import com.pblgllgs.sb3ss6securityschool.repository.StudentRepo;
import com.pblgllgs.sb3ss6securityschool.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;

    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userType);
        if(userType==UserType.ADMIN) {

            AdminEntity adminEntity = adminRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Admin Username "+ username+ "not found"));

            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.ADMIN.toString());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(adminAuthority);
            return new User(adminEntity.getUsername(), adminEntity.getPassword(), authorities);
        } else if(userType == UserType.TEACHER) {
            TeacherEntity teacherEntity = teacherRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Teacher Email "+ username+ "not found"));

            SimpleGrantedAuthority teacherAuthority = new SimpleGrantedAuthority(UserType.TEACHER.toString());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(teacherAuthority);
            return new User(teacherEntity.getEmail(), teacherEntity.getPassword(), authorities);
        } else if(userType == UserType.STUDENT) {
            StudentEntity studentEntity = studentRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Student Email "+ username+ "not found"));

            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.STUDENT.toString());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(adminAuthority);
            return new User(studentEntity.getEmail(), studentEntity.getPassword(), authorities);
        }
        return null;
    }

}
