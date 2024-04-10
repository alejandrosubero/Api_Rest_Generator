package com.api.wiki.repository;

import com.api.wiki.entitys.TaskNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskNoteRepository extends JpaRepository<TaskNote, Long> {
    public TaskNote findByTitleTaskNote(String titleTaskNote);
}
