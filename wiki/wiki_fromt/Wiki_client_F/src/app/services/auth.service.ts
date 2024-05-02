import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  authUser = signal<boolean>(false);

  setUserSystem(isUser:boolean){
    this.authUser.set(isUser);
  }
  

}
