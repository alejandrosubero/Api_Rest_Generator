import { Injectable, inject } from '@angular/core';
import { CoreService } from './core.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {
coreService:CoreService = inject(CoreService);

url:string = `api/project/create` 
// let completeUrl = `${environment.apiUrl}${this.url}create`;
  constructor() { }
}
