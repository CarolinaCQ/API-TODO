package com.example.task.service;

import com.example.task.dto.TaskDtoCreate;
import com.example.task.dto.TaskDtoUpdate;
import com.example.task.entity.Task;
import com.example.task.exception.MyException;
import com.example.task.mapper.TaskDtoToTask;
import com.example.task.mapper.TaskDtoToTaskUpdate;
import com.example.task.repository.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final ConditionService conditionService;
    private final UserService userService;
    private final TaskDtoToTaskUpdate mapperUpdate;
    private final TaskDtoToTask mapper;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAllTasksByCondition(String name) {
        return taskRepository.findByNameTaskCondition(name);
    }

    @Override
    public Task createTask(TaskDtoCreate dto) {
        return taskRepository.save(mapper.apply(dto));
    }

    
    @Override 
    @Transactional
    public Task updateTask(TaskDtoUpdate dto) {
        return taskRepository.save(mapperUpdate.apply(dto));
    }

    @Override
    @Transactional
    public Task updateCompleted(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        taskRepository.updateBoolean(id);
        return taskRepository.findById(id).get();
    }
    
    @Override
    @Transactional
    public Task updateByCondition(Long id, String nameCondition) {
        if (!taskRepository.existsById(id)) {
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        Task task = taskRepository.findById(id).get();
        task.setCondition(conditionService.getConditionByName(nameCondition));
        return taskRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void recoverDeletedTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new MyException("Task not found.", HttpStatus.NOT_FOUND);
        }
        taskRepository.recoverDeletedTask(id);
    }

}
