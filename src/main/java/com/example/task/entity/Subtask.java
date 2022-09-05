package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE subtasks SET subtask_deleted=TRUE WHERE subtask_id=?")
@Table(name="subtasks")
public class Subtask {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="subtask_id")
    private Long id;
    
    @Column(name="subtask_description")
    private String description;
    
    @Column(name="subtask_completed")
    private Boolean completed;
    
    @Column(name="subtask_deleted")
    private Boolean deleted;
    
    @JsonIgnoreProperties("subtasks")
    @ManyToOne
    private Task task;
    
}
