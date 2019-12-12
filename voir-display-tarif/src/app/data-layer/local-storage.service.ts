import { Injectable, Inject } from '@angular/core';
import { StorageService, LOCAL_STORAGE } from 'ngx-webstorage-service';
import { Product } from './model/product';

const PRODUCT_LIST_KEY = 'producs';

@Injectable({
  providedIn: 'root'
})

export class LocalStorageService {

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService) { }

  pushProducts(products: Product[]) {
    this.storage.set(PRODUCT_LIST_KEY, JSON.stringify(products));
  }

  getProducts(): Product[] {
    let products: Product[];
    const productsValue = this.storage.get(PRODUCT_LIST_KEY);
    if (productsValue) {
      products = JSON.parse(productsValue);
    }
    return products;
  }

  clearLocalStorage() {
    this.storage.clear();
  }

}
