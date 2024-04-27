import { Injectable, inject } from '@angular/core';
import { CoreService } from './core.service';
import { HttpHeaders } from '@angular/common/http';
import { ResponseObject } from '../interface/response_object';
import { environment } from '../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class ProjectsService {
coreService:CoreService = inject(CoreService);

urlProject:string = `api/project/`; 
// let completeUrl = `${environment.apiUrl}${this.url}create`;


  async handleClickForCreateProject() {
    try {
      let completeUrl = `${environment.apiUrl}${this.urlProject}create`;
      const responseData = await   this.coreService.getHttpService().getData_ResponseObject(completeUrl);
      if (responseData) {

        // Process the response data here (e.g., assign to component property)
        this.data = responseData;

      }
    } catch (error) {
       // Process the error here 
      console.error('Error fetching data:', error);
    }
  }
 
}
