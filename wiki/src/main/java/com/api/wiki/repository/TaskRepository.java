package com.api.wiki.repository;

import com.api.wiki.entitys.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTitleTask(String titleTask);
    public List<Task> findByState(String state);
    public List<Task> findByTaskType (String taskType);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO task (project_id_task) VALUES (:idproject)", nativeQuery = true)
    void insertIdProjectInTask(@Param("idproject") Long idproject);
}
