import { Injectable, EventEmitter } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Entidades } from 'src/Model/entidad.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { ContactRecibeMail } from 'src/Model/contactRecibeMail.model';

@Injectable({ providedIn: 'root' })
export class EntidadService {

    nombreEvento$ = new EventEmitter<Entidades>();
    relacionEvento$ = new EventEmitter<Entidades>();

    public entidad$ = new BehaviorSubject<Entidades>(new Entidades());
    entidadObservable = this.entidad$.asObservable();

    public articulo$ = new BehaviorSubject<ArchivoBaseDatosPojo>(new ArchivoBaseDatosPojo);
    archivo = this.articulo$.asObservable();

    constructor(private router: Router, protected http: HttpClient, ) { }

    updateArchivoPojo(archivo: ArchivoBaseDatosPojo){this.articulo$.next(archivo);}

    updateEntidad(entidad:Entidades){ 
     //   console.log('llega entidad: ', entidad);
        this.entidad$.next(entidad);
     //   console.log('despues entidad: ',  this.entidad$);
     }

    post(url: string, body: any, headers: HttpHeaders = new HttpHeaders): Observable<any> {
        headers = headers.append('Content-Type', 'application/json');
        return this.http.post(environment.serverUrl + url, body, { headers, observe: 'response' });
    }

    get(url: string, headers: HttpHeaders = new HttpHeaders): Observable<any> {
        return this.http.get(environment.serverUrl + url, { headers });
    }


   
    postP(body: ArchivoBaseDatosPojo) {
        return this.http.post( environment.serverUrl + 'api/archivosBase', body);
         // http://localhost:8888/ANACODE/Ana/archivosBase
    }

    
    postContactMe(body: ContactRecibeMail) {
        return this.http.post( environment.serverUrl + 'api/contactMe', body);
         // http://localhost:8888/ANACODE/Ana/contactMe
    }


    download(appName: string) {
        let respType = 'blob';
        let headers = new HttpHeaders();
        const requestOptions: Object = {
            // headers: headers,
            responseType: respType
        }
        return this.http.get( environment.serverUrl +'apis/downloadLogFiles/' + appName, requestOptions);
        // http://localhost:8888/ANACODE/Anas/downloadLogFiles/
    }


   

    UppercaseFirs(valor: string) { return valor.charAt(0).toUpperCase() + valor.slice(1); }

    lowercaseFirs(valor: string) { return valor.charAt(0).toLowerCase() + valor.slice(1); }


    camel(str: string): string {
        let g: Array<string> = new Array<string>();
        let cadenaArreglo: any[] = str.toLowerCase().split(' ');
        cadenaArreglo.forEach(x => { g.push(this.UppercaseFirs(x)); });
        let valor = g.join('');
        let valor1 = this.lowercaseFirs(valor);
        let valor2 = this.UppercaseFirs(valor1);
        return valor2;
    }


    artefactivalid(cadena: string): string {
        const cadenaArreglo: string = cadena.replace(/ /g, '_');
        return cadenaArreglo;
    }




}