package com.pblgllgs.sb3ss6securityschool.dto;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

public class AdminAuthDto {
    private String username;
    private String password;

    public AdminAuthDto() {
    }

    public AdminAuthDto(String username, String password) {
        this.username = username;
        this.password = password;
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
