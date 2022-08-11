package com.example.task.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoUpdate {
    
    private Long id;
    private String title;
    private String description;
    private LocalDate finishDate;
    private Integer durationInDays;
}
