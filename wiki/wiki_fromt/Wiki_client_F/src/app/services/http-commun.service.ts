import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { EntityRespone } from '../model/entityResponse.model';

@Injectable({
  providedIn: 'root'
})
export class HttpCommunService {

private readonly _http = inject(HttpClient);

  constructor() { }

  getCheck(): Observable<any>{
    return this._http.get("http://localhost:8091/wiki/control/wiki/check");
  }

  
}
