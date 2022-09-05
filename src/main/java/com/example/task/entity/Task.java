package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tasks SET task_deleted=TRUE WHERE task_id=?")
@Table(name = "tasks")
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "task_id")
    private Long id;
    
    @Column(name = "task_title", nullable = false)
    private String title;
    
    @Column(name = "task_description")
    private String description;
    
    @Column(name = "task_startDate", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "task_finishDate", nullable = false)
    private LocalDate finishDate;
    
    @Column(name = "task_duration_days", nullable = false)
    private Integer durationInDays;
    
    @Column(name = "task_completed")
    private Boolean completed;
    
    @Column(name = "task_deleted")
    private Boolean deleted;
    
    @OneToMany
    @JoinTable(name = "task_subtasks",
            joinColumns = @JoinColumn(name = "subtask_id"))
    private List<Subtask> subtasks;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_condition",  referencedColumnName = "condition_id")
    private Condition condition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("listTasks")
    @JoinColumn(name = "task_user",  referencedColumnName = "user_id")
    private User user;
    
}
