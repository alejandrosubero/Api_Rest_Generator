
import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ArchitectureService {


  public identifier$ = new BehaviorSubject<Array<string>>(new Array<string>());
  identifiersObservable = this.identifier$.asObservable();


  updateEntidad(identifiers: Array<string>) {
    this.identifier$.next(identifiers);
  }


  constructor(private router: Router, protected http: HttpClient) { }

  getIdentifiers(headers: HttpHeaders = new HttpHeaders): Observable<any> {
    return this.http.get(`${environment.serverUrl}/resource/identifiers`, { headers });
  }
  
}
