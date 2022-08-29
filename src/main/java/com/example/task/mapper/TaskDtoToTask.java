package com.example.task.mapper;

import com.example.task.dto.TaskDtoCreate;
import com.example.task.entity.Task;
import com.example.task.entity.User;
import com.example.task.repository.ConditionRepository;
import com.example.task.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDtoToTask implements Function<TaskDtoCreate, Task> {

    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    
    @Override
    public Task apply(TaskDtoCreate dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStartDate(LocalDate.now());
        task.setFinishDate(dto.getFinishDate());
        
        Period period = Period.between(dto.getFinishDate(), LocalDate.now());
        Integer durationInDays = period.getDays();
        task.setDurationInDays(durationInDays);
        
        task.setCompleted(false);
        task.setDeleted(false);
        //Condition condition = conditionRepository.findById(1L).get();
        //task.setCondition(condition);
        
        User user = userRepository.findById(dto.getIdUser()).get();
        task.setUser(user);
        user.getListTasks().add(task);
        
        
        return task;

    }

}
