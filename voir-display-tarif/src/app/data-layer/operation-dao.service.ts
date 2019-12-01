import { Injectable } from '@angular/core';
import { Product } from './model/product';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DatalayerCommonService } from './datalayer-common.service';

@Injectable({
	providedIn: 'root'
})

export class OperationDaoService {

	private operationsUrl = this.datalayerCommonService.getBaseURL() + '/api/operations';

	constructor(
		private http: HttpClient,
		private datalayerCommonService: DatalayerCommonService
	) { }

	/** POST: add a new product to the server */
	public add(product: Product): Observable<Product> {
		return this.http.post<Product>(this.operationsUrl, product, this.datalayerCommonService.getHttpOptions()).pipe(
			tap((newProduct: Product) => this.datalayerCommonService.log(`added product w/ id=${newProduct.id}`)),
			catchError(this.datalayerCommonService.handleError<Product>('addProduct'))
		);
	}

}
