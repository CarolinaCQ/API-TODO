package com.example.task.service;

import com.example.task.dto.UserDto;
import com.example.task.entity.Task;
import com.example.task.entity.User;

public interface UserService {
    
    User getUserByUsername(String username);
    User createUser(UserDto dto);
    void addTaskToUser(Task task, String username);
    void deleteUser(Long id);

}
