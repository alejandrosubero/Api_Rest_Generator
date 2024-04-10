package com.api.wiki.service;

import com.api.wiki.dto.TaskNoteDTO;

import java.util.List;

public interface TaskNoteService {
    public TaskNoteDTO findByIdTaskNote(Long idTaskNote);
    public TaskNoteDTO findByTitleTaskNote(String titleTaskNote);

    public List<TaskNoteDTO> getAll();
}
