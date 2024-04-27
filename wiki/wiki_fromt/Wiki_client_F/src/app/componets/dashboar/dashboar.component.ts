import { Component, OnInit, inject } from '@angular/core';
import { ProjectsService } from '../../services/projects.service';
import { Project } from '../../model/project.model';

@Component({
  selector: 'app-dashboar',
  standalone: true,
  imports: [],
  templateUrl: './dashboar.component.html',
  styleUrl: './dashboar.component.scss'
})
export class DashboarComponent implements OnInit {
  projectsService: ProjectsService = inject(ProjectsService);
  projectList = new Array<Project>();
  error: any;

  constructor() {}

  ngOnInit(): void {
    if (this.projectsService.listProjectSingnal().length > 0) {
      this.projectList = this.projectsService.listProjectSingnal();
      console.log('The ProjectList => ',this.projectList);
    }
  }




}
