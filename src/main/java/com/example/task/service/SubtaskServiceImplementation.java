package com.example.task.service;

import com.example.task.dto.SubtaskDtoCreate;
import com.example.task.dto.SubtaskDtoUpdate;
import com.example.task.entity.Subtask;
import com.example.task.entity.Task;
import com.example.task.exception.MyException;
import com.example.task.mapper.SubtaskDtoCreateToSubtask;
import com.example.task.mapper.SubtaskDtoUpdateToSubtask;
import com.example.task.repository.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.task.repository.SubtaskRepository;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImplementation implements SubtaskService {
    
    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;
    private final SubtaskDtoCreateToSubtask mapperCreate;
    private final SubtaskDtoUpdateToSubtask mapperUpdate;

    @Override
    @Transactional(readOnly=true)
    public List<Subtask> getAllSubtasks() {
        return subtaskRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Subtask getSubtaskById(Long id) {
        if(!subtaskRepository.existsById(id)){
            throw new MyException("Subtask not found.", HttpStatus.NOT_FOUND);
        }
        return subtaskRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Subtask createSubtask(SubtaskDtoCreate dto) {
        Subtask subtaskCreated = subtaskRepository.save(mapperCreate.apply(dto));
        addSubtaskToTask(dto.getIdTask(), subtaskCreated);
        return subtaskCreated;
    }
    
    @Override
    @Transactional
    public Subtask updateSubtask(SubtaskDtoUpdate dto) {
        return subtaskRepository.save(mapperUpdate.apply(dto));
    }

    @Override
    @Transactional
    public void deleteSubtask(Long id) {
        if(!subtaskRepository.existsById(id)){
            throw new MyException("Subtask not found.", HttpStatus.NOT_FOUND);
        }
        subtaskRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void addSubtaskToTask(Long idTask, Subtask subtask){
        Task task = taskRepository.findById(idTask).get();
        
        subtask.setTask(task);
        
        List<Subtask> listSubtasks = task.getSubtasks();
        listSubtasks.add(subtask);
    }

    @Override
    @Transactional
    public void recoverDeletedSubtask(Long id) {
        if(!subtaskRepository.existsById(id)){
            throw new MyException("Subtask not found.", HttpStatus.NOT_FOUND);
        }
        subtaskRepository.recoverDeletedSubtask(id);
    }

    @Override
    @Transactional
    public void updateCompleted(Long id) {
        if(!subtaskRepository.existsById(id)){
            throw new MyException("Subtask not found.", HttpStatus.NOT_FOUND);
        }
        subtaskRepository.updateCompleted(id);
    }
       
}
