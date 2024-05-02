import { Injectable, inject, signal } from '@angular/core';
import { CoreService } from './core.service';
import { environment } from '../../environments/environment.development';
import { Project } from '../model/project.model';


@Injectable({
  providedIn: 'root'
})
export class ProjectsService {
  coreService: CoreService = inject(CoreService);
  listProjectSingnal = signal<Array<Project>>(new Array<Project>());
  urlProject: string = `api/project/`;
  projectSingnal = signal<Project>(new Project());
  errorService:any;
  //let completeUrl = `${environment.apiUrl}${this.urlProject}create`;

  async handleClickForCreateProject() {
    try {
      let completeUrl = `${environment.apiUrl}${this.urlProject}all`;
      const responseData = await this.coreService.getHttpService().getData_ResponseObject(completeUrl);
      if (responseData) {
        const list: Array<Project> = responseData.entidades;
        if(list.length > 0){
          this.setList(list);
          console.log('ResponseData in list=> ',list);
          }
      }
    } catch (error) {
      // Process the error here 
      this.errorService = `Error fetching data: ${error}`;
      console.error('Error fetching data:', error);
    }
  }

  setList(listNew: Array<Project>) {
    this.listProjectSingnal.set(listNew);
  }

  updateList(element: Project) {
    let list: Array<Project> = this.listProjectSingnal();
    list.push(element);
    this.setList(list);
  }
}
