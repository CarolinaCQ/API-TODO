package com.example.task.service;

import com.example.task.dto.TaskDtoCreate;
import com.example.task.dto.TaskDtoUpdate;
import com.example.task.entity.Task;
import java.util.List;

public interface TaskService {
    
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> getAllTasksByCondition(String name);
    Task createTask(TaskDtoCreate dto);
    Task addUserToTask(String username, Long idTask);
    Task updateTask(TaskDtoUpdate dto);
    Task updateCompleted(Long id);
    Task updateByCondition(Long id, String nameCondition);
    void deleteTask(Long id);
}
