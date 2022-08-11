package com.example.task.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taskCondition")
public class Condition implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "taskCondition_id")
    private Long id;

    @Column(name = "taskCondition_name", nullable = false, unique = true)
    private String name;

}
