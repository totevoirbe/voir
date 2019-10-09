import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from './model/product';
import { MessageService } from 'src/app/message/message.service';
import { ProductCategoryTag } from './model/product-category-tag';
import { VatRate } from './model/vat-rate';

export enum PriceCategory {
  SdwMini = 'SDWMINI',
  SdwNormal = 'SDWNORMAL',
  SdwGeant = 'SDWGEANT',
  SdwFitMini = 'FITMINI',
  SdwFitNormal = 'FITNORMAL',
  SdwFitGeant = 'FITGEANT',
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

//  private baseURL = 'http://localhost:8080';
  private baseURL = 'http://192.168.231.132:8080';

  private productsUrl = this.baseURL + '/api/products';
  private productCategoryCatsUrl = this.baseURL + '/api/product-category-tag';
  private vatRatesUrl = this.baseURL + '/api/vat-rate';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getVatRates(): Observable<VatRate[]> {
    console.log('getVatRates()');
    return this.http.get<VatRate[]>(this.vatRatesUrl)
      .pipe(
        tap(_ => this.log('fetched VatRates')),
        catchError(this.handleError<VatRate[]>('getVatRates', []))
      );
  }

  getProductCategoryTags(): Observable<ProductCategoryTag[]> {
    console.log('getProductCategoryTags()');
    return this.http.get<ProductCategoryTag[]>(this.productCategoryCatsUrl)
      .pipe(
        tap(_ => this.log('fetched ProductCategoryTags')),
        catchError(this.handleError<ProductCategoryTag[]>('getProductCategoryTags', []))
      );
  }

  getProducts(): Observable<Product[]> {
    console.log('getProducts()');
    return this.http.get<Product[]>(this.productsUrl)
      .pipe(
        tap(_ => this.log('fetched products')),
        catchError(this.handleError<Product[]>('getProducts', []))
      );
  }

  /** GET product by id. Will 404 if id not found */
  getProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/${id}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => this.log(`fetched product id=${id}`)),
      catchError(this.handleError<Product>(`getProduct id=${id}`))
    );
  }

  /** PUT: update the product on the server */
  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.productsUrl, product, this.httpOptions).pipe(
      tap(_ => this.log(`updated product id=${product.id}`)),
      catchError(this.handleError<any>('updateProduct'))
    );
  }

  /** POST: add a new product to the server */
  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsUrl, product, this.httpOptions).pipe(
      tap((newProduct: Product) => this.log(`added product w/ id=${newProduct.id}`)),
      catchError(this.handleError<Product>('addProduct'))
    );
  }
  /** DELETE: delete the product from the server */
  deleteProduct(product: Product | number): Observable<Product> {
    const id = typeof product === 'number' ? product : product.id;
    const url = `${this.productsUrl}/${id}`;

    return this.http.delete<Product>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted product id=${id}`)),
      catchError(this.handleError<Product>('deleteProduct'))
    );
  }

  /* GET products whose name contains search term */
  searchProducts(term: string): Observable<Product[]> {
    if (!term.trim()) {
      // if not search term, return empty array.
      return of([]);
    }
    return this.http.get<Product[]>(`${this.productsUrl}/?name=${term}`).pipe(
      tap(_ => this.log(`found products matching "${term}"`)),
      catchError(this.handleError<Product[]>('searchProducts', []))
    );
  }


  getTicketLabel(pricecategory: PriceCategory, product: Product): string {
    if (PriceCategory.SdwMini === pricecategory) {
      return product.label + ' (mini)';
    } else if (PriceCategory.SdwNormal === pricecategory) {
      return product.label;
    } else if (PriceCategory.SdwGeant === pricecategory) {
      return product.label + ' (géant)';
    } else if (PriceCategory.SdwFitMini === pricecategory) {
      return product.label + ' (fit.mini)';
    } else if (PriceCategory.SdwFitNormal === pricecategory) {
      return product.label + ' (fitness)';
    } else if (PriceCategory.SdwFitGeant === pricecategory) {
      return product.label + ' (fit.géant)';
    }
    console.error('Not a category');
  }

  getPrice(pricecategory: PriceCategory, product: Product): number {
    let price: number;
    if (PriceCategory.SdwMini === pricecategory) {
      price = product.mini;
    } else if (PriceCategory.SdwNormal === pricecategory) {
      price = product.normal;
    } else if (PriceCategory.SdwGeant === pricecategory) {
      price = product.geant;
    } else if (PriceCategory.SdwFitMini === pricecategory) {
      price = product.fitmini;
    } else if (PriceCategory.SdwFitNormal === pricecategory) {
      price = product.fitnormal;
    } else if (PriceCategory.SdwFitGeant === pricecategory) {
      price = product.fitgeant;
    }
    if (price === undefined) {
      price = product.normal;
    }
    return price;
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

  /** Log a ProductService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ProductService: ${message}`);
  }
}
