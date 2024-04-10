package com.api.wiki.repository;

import com.api.wiki.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTitleTask(String titleTask);
    public List<Task> findByState(String state);
    public List<Task> findByTaskType (String taskType);
}
