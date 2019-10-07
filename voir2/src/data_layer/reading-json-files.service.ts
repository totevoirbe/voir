import { Injectable } from '@angular/core';

// import { HttpClient, HttpResponse } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @ts-ignore
// import ProductListJson from '../assets/voir_products.json';


@Injectable({
  providedIn: 'root'
})
export class ReadingJsonFilesService {

  //  private baseUrl = 'http://localhost:8080/voirBackend/rest/VoirService';

  constructor() {
    //  constructor(private httpClient: HttpClient) {

    // console.log('Reading local json files');
    // console.log(ProductListJson);

  }

  // getProductList() {


  //   console.log('getCodeTVACollectionList');
  //   const codeTVACollection = this.httpClient.get(this.baseUrl) + '/CodeTVACollection';
  //   console.log(codeTVACollection);
  //   console.log('getProductList');
  //   const productsCatalog = this.httpClient.get(this.baseUrl) + '/ProductsCatalog';
  //   console.log(productsCatalog);
  //   this.showCodeTVAResponse();
  //   this.showProductsCatalogResponse();
  //   return ProductListJson;

  // }

  // getCodeTVAResponse(): Observable<HttpResponse<CodeTVA>> {
  //   return this.httpClient.get<CodeTVA>(
  //     this.baseUrl + '/CodeTVACollection', { observe: 'response' });
  // }

  // showCodeTVAResponse() {
  //   this.getCodeTVAResponse()
  //     // resp is of type `HttpResponse<Config>`
  //     .subscribe(resp => {
  //       // display its headers
  //       const keys = resp.headers.keys();
  //       console.log(keys);
  //       console.log(resp.body);
  //     });
  // }

  // getProductsCatalogResponse(): Observable<HttpResponse<Product>> {
  //   return this.httpClient.get<Product>(
  //     this.baseUrl + '/ProductsCatalog', { observe: 'response' });
  // }

  // showProductsCatalogResponse() {
  //   this.getProductsCatalogResponse()
  //     // resp is of type `HttpResponse<Config>`
  //     .subscribe(resp => {
  //       // display its headers
  //       const keys = resp.headers.keys();
  //       console.log(keys);
  //       console.log(resp.body);
  //     });
  // }
}

export interface Product {

  code: string;
  name: string;
  productCategoryTags: string;

  vatRateOnPlace: string;
  vatRateTakeAway: string;

  mini: string;
  normal: string;
  geant: string;
  fitmini: string;
  fitnormal: string;
  fitgeant: string;

  image: string;
  htmlKeyLabel: string;
  ticketLabel: string;
  webDetail: string;
  afficheDetail: string;
  canMerge: string;

}


export interface CodeTVA {

  code: string;
  label: string;
  rate: number;

}

