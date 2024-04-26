import { inject, Injectable } from '@angular/core';

import { UserService } from './user.service';
import { CoreService } from './core.service';
import { EntityRespone } from '../model/entityResponse.model';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class DevUserService {
url:string = `api/developer/check/`; 
coreService:CoreService = inject(CoreService);
userService: UserService = this.coreService.getUserService();

checkUser(name:string): any {
  let completeUrl = `${environment.apiUrl}${this.url}${name}`;
  this.coreService.getHttpService().httpGet(completeUrl).subscribe((response:EntityRespone) =>{
      let resp = response.error ==='400'?false:true;
        if(resp){
          this.coreService.getUserService().setActiveUserSignal(response.entidades[0]);
        }
    return resp;
  },error => {
    //TODO: HAY que colocar una notificacion de error en este punto Hacer un SERVICIO
    console.error(error);
    //https://stackoverflow.com/questions/52223727/angular-should-i-subscribe-to-http-get-each-time-i-need-to-update
  });
  ;
}


}
