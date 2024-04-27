import { inject, Injectable } from '@angular/core';

import { UserService } from './user.service';
import { CoreService } from './core.service';
import { EntityRespone } from '../model/entityResponse.model';
import { environment } from '../../environments/environment.development';
import { DeveloperDTO } from '../model/developer_dto.model';


@Injectable({
  providedIn: 'root'
})
export class DevUserService {
  private url: string = `api/developer/`;
  private coreService: CoreService = inject(CoreService);

  checkUser(name: string): any {
    let completeUrl = `${environment.apiUrl}${this.url}check/${name}`;
    this.coreService.getHttpService().httpGet(completeUrl).subscribe((response: EntityRespone) => {
      let resp = response.error === '400' ? false : true;
      this.routedUser(resp, response.entidades[0]);
    }, error => {
      //TODO: HAY que colocar una notificacion de error en este punto Hacer un SERVICIO
      console.error(error);
      //https://stackoverflow.com/questions/52223727/angular-should-i-subscribe-to-http-get-each-time-i-need-to-update
    });
  }

  saveUser(developer: DeveloperDTO): void {
    let completeUrl = `${environment.apiUrl}${this.url}create`;
    this.coreService.getHttpService().httpPost(developer, completeUrl).subscribe((response: EntityRespone) => {
      let value: boolean = response.entidades[0];
      this.routedUser(value, developer.getFullName());
    }, error => {
      console.error(error);
    });
  }

  private routedUser(value: boolean, name?: string) {
    if (value && name !== null && name !== undefined ) {
      this.coreService.getUserService().setActiveUserSignal(name);
      this.coreService.navigateToRoute('dashboar')
    } else {
      this.coreService.navigateToRoute('addUser');
    }
  }


}


