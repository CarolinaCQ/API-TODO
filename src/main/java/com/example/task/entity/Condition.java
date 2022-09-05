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
import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE conditions SET condition_deleted=TRUE WHERE condition_id=?")
@Entity
@Table(name = "conditions")
public class Condition implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "condition_id")
    private Long id;

    @Column(name = "condition_name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "condition_deleted")
    private Boolean deleted;

}
