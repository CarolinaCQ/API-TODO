package com.example.task.mapper;

import com.example.task.dto.SubtaskDtoCreate;
import com.example.task.entity.Subtask;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class SubtaskDtoCreateToSubtask implements Function<SubtaskDtoCreate, Subtask>{
    
    @Override
    public Subtask apply(SubtaskDtoCreate dto) {
        
        Subtask subtask = new Subtask();
        subtask.setDescription(dto.getDescription());
        subtask.setCompleted(false);
        subtask.setDeleted(false);
        
        return subtask;
    }
    
    
}
