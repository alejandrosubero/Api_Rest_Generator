import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  activeUser = signal<string>('None');

  userSignal = signal<boolean>(false);


  setActiveUserSignal(user:string){
    this.activeUser.set(user);
    this.setUserSystem(true);
  }

  setUserSystem(isUser:boolean){
    this.userSignal.set(isUser);
  }
  
}
