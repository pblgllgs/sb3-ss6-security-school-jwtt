package com.pblgllgs.sb3ss6securityschool.entity;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Email
    private String email;
    private String password;
    private boolean status;

    @ManyToOne
    @JoinColumn(name="createdBy", referencedColumnName = "id")
    private AdminEntity createdBy;

    public AdminEntity getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(AdminEntity createdBy) {
        this.createdBy = createdBy;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


}