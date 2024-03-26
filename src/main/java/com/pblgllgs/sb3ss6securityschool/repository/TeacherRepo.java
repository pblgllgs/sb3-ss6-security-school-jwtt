package com.pblgllgs.sb3ss6securityschool.repository;

import com.pblgllgs.sb3ss6securityschool.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Integer> {
    Optional<TeacherEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
