package com.example.task.controller;

import com.example.task.dto.ConditionDto;
import com.example.task.dto.TaskDtoCreate;
import com.example.task.dto.TaskDtoUpdate;
import com.example.task.entity.Task;
import com.example.task.entity.Condition;
import com.example.task.service.ConditionServiceImplementation;
import com.example.task.service.TaskServiceImplementation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImplementation taskService;
    private final ConditionServiceImplementation conditionService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "condition", required = false) String condition) {
        if(condition!=null){
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasksByCondition(condition));
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDtoCreate dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDtoUpdate dto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(dto));
    }

    @PatchMapping("/update/completed/{id}")
    public ResponseEntity<Task> updateCompleted(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateCompleted(id));
    }
    
    @PatchMapping("/update/condition/{id}")
    public ResponseEntity<Task> updateCondition(@PathVariable Long id, @RequestParam(value="condition") String nameCondition) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateByCondition(id, nameCondition));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
