package com.api.wiki.service;

import com.api.wiki.dto.PaquetePackageDTO;
import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.dto.TaskNoteDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

public interface WriteDocumentService {

    public final String DOUBLEBREAK_LINE = "\r\n"+"\r\n";
    public final String BREAK_LINE = "\r\n";
    public final String TAB = "\t";
    public final String DOUBLETAB = "\t\t";
    public final String TITLE = "Title: ";


    default public String stringEnsamble(List<String> stringPaths) {
        StringBuilder newString = new StringBuilder();
        for (String part : stringPaths) {
            newString.append(part);
        }
        return newString.toString();
    }


    default String getPackages(List<PaquetePackageDTO> packagesDTO){
        String packages = "";
        if(packagesDTO != null && packagesDTO.size()>0){
            List<String> paquetesList = packagesDTO.stream().map(packageDTO -> packageDTO.getPackageName()).collect(Collectors.toList());
            packages = this.stringEnsamble(List.of("Package or Packages: ", BREAK_LINE, this.stringEnsamble(paquetesList)));
        }
        return packages;
    }


    default String createDocumetContentFromTask(TaskDTO taskDTO){
       return this.stringEnsamble( List.of(
               TITLE, taskDTO.getTitleTask(),BREAK_LINE,
                this.getPackages(taskDTO.getPackages()),BREAK_LINE,
               "Approach or Description: ",BREAK_LINE, taskDTO.getDescription(), BREAK_LINE
                ,"Solution: ", BREAK_LINE, taskDTO.getSolution()
                ,createDocumetContentFromSubTask(taskDTO.getSubTasks())
                ,BREAK_LINE, this.getNoteFromNotesTask(taskDTO.getTaskNote())
        ));
    }

    default String getNoteFromNotesTask(List<TaskNoteDTO> taskNotes){
        List<String> notes = new ArrayList<>();
        if(taskNotes !=null && taskNotes.size()> 0 ){
            taskNotes.forEach(taskNoteDTO -> {
                if(taskNoteDTO.getAddToDocument()){
                    notes.add( this.stringEnsamble(List.of(TITLE,
                            taskNoteDTO.getTitleTaskNote(),
                            BREAK_LINE, taskNoteDTO.getNote(),DOUBLEBREAK_LINE)
                    ));
                }
            });
            return this.stringEnsamble(List.of("Developer Notes:",BREAK_LINE, this.stringEnsamble(notes)));
        }
       return BREAK_LINE;
    }

    default String createDocumetContentFromSubTask(List<SubTaskDTO> subTaskList){
        List<String> contentFromSubTask = new ArrayList<>();
        if(subTaskList !=null && subTaskList.size()>0){
            subTaskList.forEach(subTaskDTO -> {
                contentFromSubTask.add(
                        this.stringEnsamble( List.of(TITLE, subTaskDTO.getTitleSubTask(),BREAK_LINE,
                                this.getPackages(subTaskDTO.getPackages()),BREAK_LINE,
                                "Approach or Description: ",BREAK_LINE, subTaskDTO.getDescription(), BREAK_LINE
                                ,"Solution: ", BREAK_LINE, subTaskDTO.getSolution()
                                ,BREAK_LINE, this.getNoteFromNotesTask(subTaskDTO.getTaskNote())
                                ,DOUBLEBREAK_LINE
                        )));
            });

            return this.stringEnsamble(List.of("SUB TASKS:",BREAK_LINE, this.stringEnsamble(contentFromSubTask)));
        }
        return BREAK_LINE;
    }
}
