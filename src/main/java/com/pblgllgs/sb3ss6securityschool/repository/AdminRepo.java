package com.pblgllgs.sb3ss6securityschool.repository;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import com.pblgllgs.sb3ss6securityschool.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
