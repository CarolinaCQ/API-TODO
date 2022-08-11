package com.example.task.mapper;

import com.example.task.dto.ConditionDto;
import com.example.task.entity.Condition;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class ConditionDtoToCondition implements Function<ConditionDto, Condition> {

    @Override
    public Condition apply(ConditionDto dto) {
        
        Condition condition = new Condition();
        condition.setName(dto.getName());
        
        return condition;
    }
    
    
}
