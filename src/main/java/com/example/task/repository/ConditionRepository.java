package com.example.task.repository;

import com.example.task.entity.Condition;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long>{
    
    public Optional<Condition> findById(Long id);
    
    public Optional<Condition> findByName(String name);
    
    public boolean existsById(Long id);
    
    public boolean existsByName(String name);
    
    @Modifying
    @Query("UPDATE Condition c SET c.deleted=FALSE WHERE id=:id")
    public void recoverDeletedCondition(@Param("id") Long id);
}
