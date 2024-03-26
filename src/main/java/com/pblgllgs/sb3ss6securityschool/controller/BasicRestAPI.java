package com.pblgllgs.sb3ss6securityschool.controller;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class BasicRestAPI {
    @GetMapping("/home")
    public ResponseEntity<String> publicHome(){
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
