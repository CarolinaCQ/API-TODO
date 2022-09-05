package com.example.task.repository;

import com.example.task.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    public Optional<Role> findById(Long id);
    
    public boolean existsById(Long id);
    
    @Modifying
    @Query("UPDATE Role r SET r.deleted=FALSE WHERE id=:id")
    public void recoverDeletedRole(@Param("id") Long id);
}
