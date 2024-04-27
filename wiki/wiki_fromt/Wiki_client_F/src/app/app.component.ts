import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './componets/header/header.component';
import { EntityRespone } from './model/entityResponse.model';
import { CoreService } from './services/core.service';
import { ProjectsService } from './services/projects.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'Wiki_client';
  coreService: CoreService = inject(CoreService);
  responseTest: EntityRespone | undefined;
  private projectsService: ProjectsService = inject(ProjectsService);


  ngOnInit(): void {
    this.coreService.getHttpService().httpGetCheck().subscribe(x => {
      this.responseTest = x;
      console.log(this.responseTest?.mensaje);
      this.projectsService.handleClickForCreateProject();
    });
  }


}
