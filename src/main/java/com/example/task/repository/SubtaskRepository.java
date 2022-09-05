package com.example.task.repository;

import com.example.task.entity.Subtask;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    
    public boolean existsById(Long id);
    
    public Optional<Subtask> findById(Long id);
    
    @Modifying
    @Query("UPDATE Subtask s SET s.deleted=FALSE WHERE id=:id")
    public void recoverDeletedSubtask(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE Subtask s SET s.completed=TRUE WHERE id=:id")
    public void updateCompleted(@Param("id") Long id);
}
