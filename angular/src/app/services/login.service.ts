import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { environment } from './../../environments/environment';
import { Usuario } from '../model/usuario';
import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class LoginService {

  private backUrl = environment.backUrl;  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private storageService: StorageService) { }




  login(username: string, password: string): Observable<Usuario> {
    const userPass = username + ':' + password;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + this._utf8_to_b64(userPass)
    });
    return this.http.get<any>(this.backUrl + '/login', {
      headers: headers,
      observe: 'response'
    })
      .pipe(
        map(response => {
          console.log(response);
          const xAuthToken = response.headers.get('x-auth-token');
          console.log(xAuthToken);
          this.storageService.setAuthToken(xAuthToken);
          const usuario = new Usuario(response.body);
          this.storageService.setUsuario(usuario);
          return usuario;
        }),
        tap(usuario => {
          console.log(usuario);
          this.log('user ' + usuario.username + ' logged in');
        }),
        catchError(this.handleError<Usuario>('login', null))
      );
  }

  logout(): Observable<Usuario> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'x-auth-token': this.storageService.getAuthToken()
    });
    return this.http.get<any>(this.backUrl + '/logout', { headers: headers })
      .pipe(
        tap(res => {
          console.log(res);
          this.storageService.setUsuario(null);
          this.storageService.setAuthToken(null);
          this.log('user logged out');
        }),
        catchError(this.handleError<Usuario>('login', null))
      );
  }
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a CentroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`LoginService: ${message}`);
  }

  private _utf8_to_b64(text: string) {
    return btoa(encodeURIComponent(text).replace(/%([0-9A-F]{2})/g, (match, p1) => {
      return String.fromCharCode('0x' as any + p1);
    }));
  }
}