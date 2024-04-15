package com.api.wiki.repository;

import com.api.wiki.entitys.TaskNote;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskNoteRepository extends JpaRepository<TaskNote, Long> {
    public TaskNote findByTitleTaskNote(String titleTaskNote);


}
