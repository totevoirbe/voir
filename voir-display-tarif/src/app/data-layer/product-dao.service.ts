import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from './model/product';
import { ProductCategoryTag } from './model/product-category-tag';
import { VatRate } from './model/vat-rate';
import { DatalayerCommonService } from './datalayer-common.service';
import { PriceCategory } from './model/enumValues';

@Injectable({
  providedIn: 'root'
})


export class ProductDaoService {

  private productsUrl = this.datalayerCommonService.getBaseURL() + '/api/products';
  private productCategoryCatsUrl = this.datalayerCommonService.getBaseURL() + '/api/product-category-tag';
  private vatRatesUrl = this.datalayerCommonService.getBaseURL() + '/api/vat-rate';

  constructor(
    private http: HttpClient,
    private datalayerCommonService: DatalayerCommonService
  ) { }

  getVatRates(): Observable<VatRate[]> {
    console.log('getVatRates()');
    return this.http.get<VatRate[]>(this.vatRatesUrl)
      .pipe(
        tap(_ => this.datalayerCommonService.log('fetched VatRates')),
        catchError(this.datalayerCommonService.handleError<VatRate[]>('getVatRates', []))
      );
  }

  getProductCategoryTags(): Observable<ProductCategoryTag[]> {
    console.log('getProductCategoryTags()');
    return this.http.get<ProductCategoryTag[]>(this.productCategoryCatsUrl)
      .pipe(
        tap(_ => this.datalayerCommonService.log('fetched ProductCategoryTags')),
        catchError(this.datalayerCommonService.handleError<ProductCategoryTag[]>('getProductCategoryTags', []))
      );
  }

  getProducts(): Observable<Product[]> {
    console.log('getProducts()');
    return this.http.get<Product[]>(this.productsUrl)
      .pipe(
        tap(_ => this.datalayerCommonService.log('fetched products')),
        catchError(this.datalayerCommonService.handleError<Product[]>('getProducts', []))
      );
  }

  /** GET product by id. Will 404 if id not found */
  getProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/${id}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => this.datalayerCommonService.log(`fetched product id=${id}`)),
      catchError(this.datalayerCommonService.handleError<Product>(`getProduct id=${id}`))
    );
  }

  /** PUT: update the product on the server */
  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.productsUrl, product, this.datalayerCommonService.getHttpOptions()).pipe(
      tap(_ => this.datalayerCommonService.log(`updated product id=${product.id}`)),
      catchError(this.datalayerCommonService.handleError<any>('updateProduct'))
    );
  }

  /** POST: add a new product to the server */
  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsUrl, product, this.datalayerCommonService.getHttpOptions()).pipe(
      tap((newProduct: Product) => this.datalayerCommonService.log(`added product w/ id=${newProduct.id}`)),
      catchError(this.datalayerCommonService.handleError<Product>('addProduct'))
    );
  }
  /** DELETE: delete the product from the server */
  deleteProduct(product: Product | number): Observable<Product> {
    const id = typeof product === 'number' ? product : product.id;
    const url = `${this.productsUrl}/${id}`;

    return this.http.delete<Product>(url, this.datalayerCommonService.getHttpOptions()).pipe(
      tap(_ => this.datalayerCommonService.log(`deleted product id=${id}`)),
      catchError(this.datalayerCommonService.handleError<Product>('deleteProduct'))
    );
  }

  /* GET products whose name contains search term */
  searchProducts(term: string): Observable<Product[]> {
    if (!term.trim()) {
      // if not search term, return empty array.
      return of([]);
    }
    return this.http.get<Product[]>(`${this.productsUrl}/?name=${term}`).pipe(
      tap(_ => this.datalayerCommonService.log(`found products matching "${term}"`)),
      catchError(this.datalayerCommonService.handleError<Product[]>('searchProducts', []))
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
}
