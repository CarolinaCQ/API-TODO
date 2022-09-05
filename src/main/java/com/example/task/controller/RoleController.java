package com.example.task.controller;

import com.example.task.dto.RoleDto;
import com.example.task.entity.Role;
import com.example.task.service.RoleServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    
    private final RoleServiceImplementation roleService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody RoleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(dto));
    }
    
    @PatchMapping("/recover/{id}")
    public ResponseEntity<Void> recoverDeletedRole(@PathVariable Long id){
        roleService.recoverDeletedRole(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
