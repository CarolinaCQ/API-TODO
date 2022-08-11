package com.example.task.mapper;

import com.example.task.dto.TaskDtoCreate;
import com.example.task.entity.Task;
import com.example.task.entity.Condition;
import com.example.task.exception.MyException;
import com.example.task.repository.ConditionRepository;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDtoToTask implements Function<TaskDtoCreate, Task> {

    private final ConditionRepository conditionRepository;
    
    @Override
    public Task apply(TaskDtoCreate dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStartDate(LocalDate.now());
        task.setFinishDate(dto.getFinishDate());
        if (!Objects.isNull(dto.getDurationInDays())) {
            if (dto.getDurationInDays() < 0) {
                throw new MyException("The duration in days must be greater than zero", HttpStatus.BAD_REQUEST);
            }
            task.setDurationInDays(dto.getDurationInDays());
        }
        task.setCompleted(false);
        Condition condition = conditionRepository.findById(1L).get();
        task.setCondition(condition);
        
        return task;

    }

}
