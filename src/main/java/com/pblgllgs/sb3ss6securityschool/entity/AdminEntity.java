package com.pblgllgs.sb3ss6securityschool.entity;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */


import jakarta.persistence.*;

@Entity
@Table(name="admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}