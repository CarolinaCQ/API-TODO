package com.example.task.service;

import com.example.task.dto.SubtaskDtoCreate;
import com.example.task.dto.SubtaskDtoUpdate;
import com.example.task.entity.Subtask;
import java.util.List;

public interface SubtaskService {
    
    List<Subtask> getAllSubtasks();
    Subtask getSubtaskById(Long id);
    Subtask createSubtask(SubtaskDtoCreate dto);
    Subtask updateSubtask(SubtaskDtoUpdate dto);
    void updateCompleted(Long id);
    void addSubtaskToTask(Long idTask, Subtask subtask);
    void deleteSubtask(Long id);
    void recoverDeletedSubtask(Long id);
    
}
