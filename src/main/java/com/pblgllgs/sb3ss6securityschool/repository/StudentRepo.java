package com.pblgllgs.sb3ss6securityschool.repository;

import com.pblgllgs.sb3ss6securityschool.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}