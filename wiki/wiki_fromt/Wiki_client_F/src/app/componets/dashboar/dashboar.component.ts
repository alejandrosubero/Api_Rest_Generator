import { Component, OnInit, inject } from '@angular/core';
import { ProjectsService } from '../../services/projects.service';
import { Project } from '../../model/project.model';
import { ButtonModule } from 'primeng/button';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


interface AutoCompleteCompleteEvent {
  originalEvent: Event;
  query: string;
}


@Component({
  selector: 'app-dashboar',
  standalone: true,
  imports: [ButtonModule, AutoCompleteModule,CommonModule, FormsModule],
  templateUrl: './dashboar.component.html',
  styleUrl: './dashboar.component.scss'
})
export class DashboarComponent implements OnInit {
  projectsService: ProjectsService = inject(ProjectsService);
  projectList = new Array<Project>();
  error: any;
  selectedOption = new Project();

  ngOnInit(): void {
    if (this.projectsService.listProjectSingnal().length > 0) {
      this.projectsService.listProjectSingnal().forEach((project:Project)=>{
        console.log('prod=>>',project.name);
    
        this.projectList.push(project);
      });
      console.log('prod=>>',this.projectList[0].name);
    }
  }

  onSelectChange(event: string){
    let prod  = this.projectList.find(project => project.name === event);
    console.log('prod',prod);
  }


  onCreateNewProject(){
    // do 
  }
}
