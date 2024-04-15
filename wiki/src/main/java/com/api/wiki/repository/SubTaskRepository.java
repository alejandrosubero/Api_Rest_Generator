package com.api.wiki.repository;

import com.api.wiki.entitys.SubTask;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    public SubTask findByTitleSubTask(String titleTask);
    public List<SubTask> findByState(String state);
    public List<SubTask> findByTaskType (String taskType);


    @Modifying
    @Transactional
    @Query(value = "UPDATE sub_task SET id_task_sub_task = :idTask WHERE id_sub_task_ob = :id", nativeQuery = true)
    void updateIdProjectInTask(Long idTask, Long id);

}

