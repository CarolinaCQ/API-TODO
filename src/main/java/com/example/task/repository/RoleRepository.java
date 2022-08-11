package com.example.task.repository;

import com.example.task.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    public Optional<Role> findById(Long id);
    
    public boolean existsById(Long id);
}
