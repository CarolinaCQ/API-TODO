package com.example.task.repository;

import com.example.task.entity.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Task t SET t.completed=TRUE WHERE t.id=:id")
    public void updateBoolean(@Param("id") Long id);

    public boolean existsById(Long id);

    public Optional<Task> findById(Long id);

    @Query("SELECT t FROM Task t WHERE t.condition.name=:name")
    public List<Task> findByNameTaskCondition(@Param("name") String name);

    @Modifying
    @Query("UPDATE Task t SET t.deleted=FALSE WHERE id=:id")
    public void recoverDeletedTask(@Param("id") Long id);
}
