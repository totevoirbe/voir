import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DatalayerCommonService {

   private baseURL = 'http://localhost:8080';
  // private baseURL = 'http://192.168.231.136:8080';


  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  constructor(
    private messageService: MessageService
  ) { }


  public getHttpOptions() {
    return this.httpOptions;
  }


  getBaseURL(): string {
    return this.baseURL;
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  public handleError<T>(operation = 'operation', result?: T) {

    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


  /** Log a ProductService message with the MessageService */
  public log(message: string) {
    this.messageService.add(`ProductService: ${message}`);
  }


}
