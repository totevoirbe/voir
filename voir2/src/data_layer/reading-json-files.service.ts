import { Injectable } from '@angular/core';
// @ts-ignore
import ProductListJson from '../assets/voir_products.json';


@Injectable({
  providedIn: 'root'
})
export class ReadingJsonFilesService {

  constructor() {
    console.log('Reading local json files');
    console.log(ProductListJson);
  }

  getProductList() {
    console.log('getProductList');
    return ProductListJson;
  }

}
