import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { EntityRespone } from '../model/entityResponse.model';

@Injectable({
  providedIn: 'root'
})
export class HttpCommunService {

private readonly _http = inject(HttpClient);

  constructor() { }

  httpGetCheck(): Observable<any>{
    return this._http.get(`${environment.apiUrl}control/wiki/check`);
  }

  httpGet(url:string): Observable<any>{
    return this._http.get(url);
  }

  httpPost(body: any, url:string): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this._http.post(url, body, { headers });
  }

  httpPut(body: any, url:string): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this._http.put(url, body, { headers });
  }

  httpGetG(url:string): Observable<any>{
    return this._http.get(url);
  }

  getData(url:string): Observable<EntityRespone> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this._http.get<EntityRespone>(url, options);
  }
}
