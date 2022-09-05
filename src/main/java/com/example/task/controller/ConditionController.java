package com.example.task.controller;

import com.example.task.dto.ConditionDto;
import com.example.task.entity.Condition;
import com.example.task.service.ConditionServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conditions")
@RequiredArgsConstructor
public class ConditionController {
    
    private final ConditionServiceImplementation conditionService;
    
    @PostMapping("/create")
    public ResponseEntity<Condition> createRole(@RequestBody ConditionDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(conditionService.createCondition(dto));
    }
    
    @PatchMapping("/recover/{id}")
    public ResponseEntity<Void> recoverDeletedCondition(@PathVariable Long id){
        conditionService.recoverDeletedCondition(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } 
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id){
        conditionService.deleteCondition(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
            
}
