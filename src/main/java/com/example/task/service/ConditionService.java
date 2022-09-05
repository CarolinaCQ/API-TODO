package com.example.task.service;

import com.example.task.dto.ConditionDto;
import com.example.task.entity.Condition;

public interface ConditionService {
    
    Condition getConditionByName(String name);
    Condition createCondition(ConditionDto condition);
    void deleteCondition(Long id);
    void recoverDeletedCondition(Long id);
}
