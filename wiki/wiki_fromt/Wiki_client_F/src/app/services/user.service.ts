import { inject, Injectable, signal } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  authService: AuthService = inject(AuthService);
  activeUser = signal<string>('None');
  userSignal = this.authService.authUser();


  setActiveUserSignal(user:string){
    this.activeUser.set(user);
    this.authService.setUserSystem(true);
  }
  
}
