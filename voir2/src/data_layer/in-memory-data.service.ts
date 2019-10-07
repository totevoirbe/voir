import { Injectable } from '@angular/core';

import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Product } from 'src/app/data_models/product';
import { ReadingJsonFilesService } from './reading-json-files.service';

@Injectable({
  providedIn: 'root'
})

export class InMemoryDataService implements InMemoryDbService {

  constructor(private readingJsonFiles: ReadingJsonFilesService) { }


  createDb() {

//    console.log('create DB');
    // const products = this.readingJsonFiles.getProductList();
    // console.log('Db created');
    // return { products };

    return null;

  }

  // Overrides the genId method to ensure that a product always has an id.
  // If the products array is empty,
  // the method below returns the initial number (11).
  // if the products array is not empty, the method below returns the highest
  // product id + 1.
  genId(products: Product[]): number {
    return products.length > 0 ? Math.max(...products.map(product => product.id)) + 1 : 11;
  }
}
