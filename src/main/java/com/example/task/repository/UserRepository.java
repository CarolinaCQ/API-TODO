package com.example.task.repository;

import com.example.task.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    public Optional<User> findByUsername(String Username);
    
    public boolean existsByUsername(String username);
    
}
