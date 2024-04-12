import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IdiomaService {

  subject$ = new BehaviorSubject('es');

  constructor() { }

  public cambioIdioma(msj:string): void {
		this.subject$.next(msj);
	}


}
