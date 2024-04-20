package com.api.wiki.repository;

import com.api.wiki.entitys.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTitleTask(String titleTask);

    public List<Task> findByState(String state);

    public List<Task> findByTaskType(String taskType);

    public List<Task> findByIdProject (Long idProject);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO task (project_id_task) VALUES (:idproject)", nativeQuery = true)
    void insertIdProjectInTask(@Param("idproject") Long idproject);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET project_id_task = :idproject WHERE task_id = :id", nativeQuery = true)
    void updateIdProjectInTask(Long idproject, Long id);



    //    @Modifying
//    @Transactional
//    @Query(value = "UPDATE Task SET idproject = ?1 WHERE taskId = ?2", nativeQuery = false)
//    void updateIdProjectInTask(Long idproject, Long id);


}

//UPDATE task SET project_id_task = :idproject WHERE task_id = :id;