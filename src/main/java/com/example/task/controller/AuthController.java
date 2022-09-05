/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task.controller;

import com.example.task.dto.UserDto;
import com.example.task.entity.User;
import com.example.task.service.UserServiceImplementation;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserServiceImplementation userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response){
        return ResponseEntity.status(HttpStatus.OK).body(response.getContentType());
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }
    
}
