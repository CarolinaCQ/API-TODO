package com.example.task.mapper;

import com.example.task.dto.SubtaskDtoUpdate;
import com.example.task.entity.Subtask;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.task.repository.SubtaskRepository;

@Component
@RequiredArgsConstructor
public class SubtaskDtoUpdateToSubtask implements Function<SubtaskDtoUpdate, Subtask> {

    private final SubtaskRepository stepRepository;
    
    @Override
    public Subtask apply(SubtaskDtoUpdate dto) {

        Subtask subtask = stepRepository.findById(dto.getId()).get();
        subtask.setDescription(dto.getDescription());
        
        return subtask;
    }
    
}
