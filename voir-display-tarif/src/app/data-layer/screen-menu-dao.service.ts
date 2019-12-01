import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { DatalayerCommonService } from './datalayer-common.service';
import { ScreenMenuItem } from './model/screenMenuItem';

@Injectable({
  providedIn: 'root'
})
export class ScreenMenuDaoService {

  private screenMenuUrl = this.datalayerCommonService.getBaseURL() + '/api/screenMenu';

  constructor(
    private http: HttpClient,
    private datalayerCommonService: DatalayerCommonService
  ) { }

  getScreenMenuList(): Observable<ScreenMenuItem[]> {
    console.log('getScreenMenuList()');
    return this.http.get<ScreenMenuItem[]>(this.screenMenuUrl)
      .pipe(
        tap(_ => this.datalayerCommonService.log('fetched ScreenMenuItem')),
        catchError(this.datalayerCommonService.handleError<ScreenMenuItem[]>('getScreenMenuItem', []))
      );
  }


}
