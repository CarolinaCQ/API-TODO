package com.example.task.service;

import com.example.task.dto.ConditionDto;
import com.example.task.entity.Condition;
import com.example.task.exception.MyException;
import com.example.task.mapper.ConditionDtoToCondition;
import com.example.task.repository.ConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConditionServiceImplementation implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final ConditionDtoToCondition mapper;

    @Override
    public Condition getConditionByName(String name) {
        if (!conditionRepository.existsByName(name)) {
            throw new MyException("Condition not found.", HttpStatus.NOT_FOUND);
        }
        return conditionRepository.findByName(name).get();
    }

    @Override
    public Condition createCondition(ConditionDto dto) {
        return conditionRepository.save(mapper.apply(dto));
    }

    @Override
    @Transactional
    public void deleteCondition(Long id) {
        if (!conditionRepository.existsById(id)) {
            throw new MyException("Condition not found.", HttpStatus.NOT_FOUND);
        }
        conditionRepository.deleteById(id);
    }

}
