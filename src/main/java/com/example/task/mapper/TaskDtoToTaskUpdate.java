package com.example.task.mapper;

import com.example.task.dto.TaskDtoCreate;
import com.example.task.dto.TaskDtoUpdate;
import com.example.task.entity.Task;
import com.example.task.exception.MyException;
import com.example.task.repository.ConditionRepository;
import com.example.task.repository.TaskRepository;
import java.util.Objects;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDtoToTaskUpdate implements Function<TaskDtoUpdate, Task> {

    private final TaskRepository taskRepository;
    private final ConditionRepository conditionRepository;
    
    @Override
    public Task apply(TaskDtoUpdate dto) {
        if(!taskRepository.existsById(dto.getId())){
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        
        Task task = taskRepository.findById(dto.getId()).get();
        
        if(!Objects.isNull(dto.getTitle())) task.setTitle(dto.getTitle());
        if(!Objects.isNull(dto.getDescription())) task.setDescription(dto.getDescription());
        if(!Objects.isNull(dto.getFinishDate())) task.setFinishDate(dto.getFinishDate());
        if(!Objects.isNull(dto.getDurationInDays())){
            if(dto.getDurationInDays()<0){
                throw new MyException("The duration in days must be greater than zero", HttpStatus.BAD_REQUEST);
            }
            dto.setDurationInDays(dto.getDurationInDays());
        }
        
        return task;
    }
    
}
