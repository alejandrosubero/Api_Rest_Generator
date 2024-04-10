package com.api.wiki.repository;

import com.api.wiki.entitys.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    public SubTask findByTitleSubTask(String titleTask);
    public List<SubTask> findByState(String state);
    public List<SubTask> findByTaskType (String taskType);

}
