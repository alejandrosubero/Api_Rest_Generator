import { inject, Injectable } from '@angular/core';
import { UserService } from './user.service';
import { HttpCommunService } from './http-commun.service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class CoreService {

  private userService: UserService = inject(UserService);
  private httpCommunService: HttpCommunService = inject(HttpCommunService);
 

  constructor(private router: Router) { }

  navigateToRoute(route: string) {
    this.router.navigate([`/${route}`]);
  }

  getUserService() {
    return this.userService;
  }

  getHttpService() {
    return this.httpCommunService;
  }


}
