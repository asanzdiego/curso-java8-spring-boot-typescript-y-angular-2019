import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Centro } from '../model/centro';
import { MessageService } from './message.service';
import { environment } from './../../environments/environment';
import { StorageService } from './storage.service';

@Injectable({ providedIn: 'root' })
export class CentroService {

  private centrosUrl = environment.backUrl + '/centros/';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private storageService: StorageService) {
  }

  private getHttpOptions() {

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Auth-Token', this.storageService.getAuthToken());
    return { headers: headers };
  }

  /** GET centros from the server */
  getCentros(): Observable<Centro[]> {
    return this.http.get<Centro[]>(this.centrosUrl, this.getHttpOptions())
      .pipe(
        map(res => {
          console.log(res);
          return Centro.fromJsonToArray(res);
        }),
        tap(items => {
          console.log(items);
          this.log('fetched centros');
        }),
        catchError(this.handleError<Centro[]>('getCentros', []))
      );
  }

  /** GET centro by id. Return `undefined` when id not found */
  getCentroNo404<Data>(id: number): Observable<Centro> {
    const url = `${this.centrosUrl}/?id=${id}`;
    return this.http.get<Centro[]>(url, this.getHttpOptions())
      .pipe(
        map(centros => centros[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? `fetched` : `did not find`;
          this.log(`${outcome} centro id=${id}`);
        }),
        catchError(this.handleError<Centro>(`getCentro id=${id}`))
      );
  }

  /** GET centro by id. Will 404 if id not found */
  getCentro(id: number): Observable<Centro> {
    const url = `${this.centrosUrl}/${id}`;
    return this.http.get<Centro>(url, this.getHttpOptions()).pipe(
      tap(_ => this.log(`fetched centro id=${id}`)),
      catchError(this.handleError<Centro>(`getCentro id=${id}`))
    );
  }

  /* GET centros whose name contains search term */
  searchCentros(term: string): Observable<Centro[]> {
    if (!term.trim()) {
      // if not search term, return empty centro array.
      return of([]);
    }
    return this.http.get<Centro[]>(`${this.centrosUrl}/?name=${term}`, this.getHttpOptions()).pipe(
      tap(_ => this.log(`found centros matching "${term}"`)),
      catchError(this.handleError<Centro[]>('searchCentros', []))
    );
  }

  /** POST: add a new centro to the server */
  addCentro(centro: Centro): Observable<Centro> {
    return this.http.post<Centro>(this.centrosUrl, centro, this.getHttpOptions()).pipe(
      tap((newCentro: Centro) => this.log(`added centro w/ id=${newCentro.codigo}`)),
      catchError(this.handleError<Centro>('addCentro'))
    );
  }

  /** DELETE: delete the centro from the server */
  deleteCentro(centro: Centro | number): Observable<Centro> {
    const id = typeof centro === 'number' ? centro : centro.codigo;
    const url = `${this.centrosUrl}/${id}`;

    return this.http.delete<Centro>(url, this.getHttpOptions()).pipe(
      tap(_ => this.log(`deleted centro id=${id}`)),
      catchError(this.handleError<Centro>('deleteCentro'))
    );
  }

  /** PUT: update the centro on the server */
  updateCentro(centro: Centro): Observable<any> {
    return this.http.put(this.centrosUrl + '/' + centro.codigo, centro, this.getHttpOptions()).pipe(
      tap(_ => this.log(`updated centro id=${centro.codigo}`)),
      catchError(this.handleError<any>('updateCentro'))
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
    this.messageService.add(`CentroService: ${message}`);
  }
}