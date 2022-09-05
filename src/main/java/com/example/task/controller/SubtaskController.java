package com.example.task.controller;

import com.example.task.dto.SubtaskDtoCreate;
import com.example.task.dto.SubtaskDtoUpdate;
import com.example.task.entity.Subtask;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.task.service.SubtaskService;

@RestController
@RequestMapping("task-subtasks")
@RequiredArgsConstructor
public class SubtaskController {
    
    private final SubtaskService subtaskService;
    
    @GetMapping
    public ResponseEntity<List<Subtask>> getAllSubtasks() {
        return ResponseEntity.status(HttpStatus.OK).body(subtaskService.getAllSubtasks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Subtask> getSubtaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskService.getSubtaskById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Subtask> createTask(@RequestBody SubtaskDtoCreate dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskService.createSubtask(dto));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Subtask> updateSubtask (@RequestBody SubtaskDtoUpdate dto) {
        return ResponseEntity.status(HttpStatus.OK).body(subtaskService.updateSubtask(dto));
    }
    
    @PatchMapping("/update/completed/{id}")
    public ResponseEntity<Void> updateSubtask (@PathVariable Long id) {
        subtaskService.updateCompleted(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PatchMapping("/recover/{id}")
    public ResponseEntity<Void> recoverDeletedSubtask (@PathVariable Long id) {
        subtaskService.recoverDeletedSubtask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Long id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
