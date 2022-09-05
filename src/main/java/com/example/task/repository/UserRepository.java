package com.example.task.repository;

import com.example.task.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    public Optional<User> findByUsername(String Username);
    
    public boolean existsByUsername(String username);
    
    @Modifying
    @Query("UPDATE User u SET u.deleted=FALSE WHERE id=:id")
    public void recoverDeletedUser(@Param("id") Long id);
    
}
