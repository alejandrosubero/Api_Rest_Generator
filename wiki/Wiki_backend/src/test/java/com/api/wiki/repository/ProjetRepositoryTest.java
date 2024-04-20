package com.api.wiki.repository;

import com.api.wiki.entitys.*;
import com.api.wiki.utility.TaskSate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.api.wiki.utility.TaskType.TASK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;



@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProjetRepositoryTest {

        @Autowired
        private com.api.wiki.repository.ProjectRepository projectRepository;

        private Project getProyect(){

            VersionControl versionControl = VersionControl.builder()
                    .version("1.0.0")
                    .description("version of test")
                    .documentList(List.of(
                            Document.builder()
                                    .actualVersion("1.0.0").title("first doc test")
                                    .active(true).content("This is it test document for document all program")
                                    .createDate(new Date()).referentVersion("0").docReferentNumber("h3321-a").build(),
                            Document.builder()
                                    .actualVersion("1.0.0").title("two doc test")
                                    .active(true).content("This is it test of document number 2 for document all program")
                                    .createDate(new Date()).referentVersion("0").docReferentNumber("h3321-b").build()
                    ))
                    .build();

            Developer developer = Developer.builder()
                    .name("alejandro").lastName("subero").ingreseDate(new Date()).build();

            TaskNote taskNote = TaskNote.builder().titleTaskNote("test nota 1").note("test number 1").build();
            TaskNote taskNote2 = TaskNote.builder().titleTaskNote("test nota 2").note("test number 3").build();

            Task task = Task.builder()
                    .titleTask("test general Task")
                    .description("task for test")
                    .state(TaskSate.CREATE.toString())
                    .taskType(TASK.toString())
                    .createDate(new Date())
                    .solution("solution for a task")
                    .personCreate(developer.getLastName())
                    .personWorked(developer.getLastName())
                    .taskNote(List.of(taskNote,taskNote2))
                    .packages(List.of(PaquetePackage.builder().packageName("Entitys").build()))
                    .time(23L)
                    .taskReferenceNumber("ABQ-23")
                    .subTasks(List.of(
                            SubTask.builder()
                                    .titleSubTask("subtASK 1 test ")
                                    .description("subtASK for test")
                                    .state(TaskSate.CREATE.toString())
                                    .taskType(TASK.toString())
                                    .createDate(new Date())
                                    .solution("solution for a subtASK")
                                    .personCreate(developer.getLastName())
                                    .personWorked(developer.getLastName())
                                    .taskNote(List.of(taskNote,taskNote2))
                                    .packages(List.of(PaquetePackage.builder().packageName("Entitys").build()))
                                    .time(23L)
                                    .taskReferenceId(25L)
                                    .build(),
                            SubTask.builder()
                                    .titleSubTask("subtASK 2 test ")
                                    .description("subtASK for test")
                                    .state(TaskSate.CREATE.toString())
                                    .taskType(TASK.toString())
                                    .createDate(new Date())
                                    .solution("solution for a subtASK")
                                    .personCreate(developer.getLastName())
                                    .personWorked(developer.getLastName())
                                    .taskNote(List.of(taskNote,taskNote2))
                                    .packages(List.of(PaquetePackage.builder().packageName("Entitys").build()))
                                    .time(23L)
                                    .taskReferenceId(25L)
                                    .build()
                    ))
                    .build();


            return Project.builder()
                    .idProject(1l)
                    .name("test proyect")
                    .repositoryLink("http:test./repository.com")
                    .description("test of repository")
                    .time(10l)
                    .startDate(new Date())
                    .versionControlList(List.of(versionControl))
                    .taskList(List.of(task))
                    .build();
        }

        @Test
        public void saveProjetAndReturnProjet() {
            Project project = this.getProyect();
            project.setIdProject(25l);
            //Act
            Project pro = projectRepository.save(project);
            //Assert
            Assertions.assertThat(pro).isNotNull();
            Assertions.assertThat(pro.getIdProject()).isGreaterThan(0);
        }
}
