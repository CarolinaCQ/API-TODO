package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET user_deleted=TRUE WHERE user_id=?")
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "user_username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "user_password", nullable = false)
    private String password;
    
    @Column(name = "user_deleted")
    private Boolean deleted;
    
    @ManyToMany(fetch = EAGER)
    private List<Role> roles;
    
    @OneToMany(mappedBy="user")
    @JsonIgnoreProperties("user")
    private List<Task> listTasks;
    
}
