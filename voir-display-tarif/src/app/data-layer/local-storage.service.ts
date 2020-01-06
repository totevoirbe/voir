import { Injectable, Inject } from '@angular/core';
import { StorageService, LOCAL_STORAGE } from 'ngx-webstorage-service';
import { Product } from './model/product';

const PRODUCT_LIST_KEY = 'producs';

export enum LOCAL_STORAGE_STATUS {
  Valid = 'VALID',
  NotValid = 'NOT_VALID',
  Error = 'ERROR'
}

@Injectable({
  providedIn: 'root'
})

export class LocalStorageService {

  storageServiceValid: LOCAL_STORAGE_STATUS = LOCAL_STORAGE_STATUS.NotValid;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService) {

    this.storageServiceValid = LOCAL_STORAGE_STATUS.NotValid;

    try {
      const txtTest = 'TEST';
      if (storage) {
        storage.set(txtTest, txtTest);
        if (storage.get(txtTest) === txtTest) {
          // FOR TEST
          this.storageServiceValid = LOCAL_STORAGE_STATUS.NotValid;
        }
        storage.remove(txtTest);
      }

    } catch (exception) { this.storageServiceValid = LOCAL_STORAGE_STATUS.Error; }
  }

  isStorageServiceValid(): LOCAL_STORAGE_STATUS {
    return this.storageServiceValid;
  }

  pushProducts(products: Product[]) {
    if (this.storageServiceValid === LOCAL_STORAGE_STATUS.Valid) {
      this.storage.set(PRODUCT_LIST_KEY, JSON.stringify(products));
    }
  }

  getProducts(): Product[] {
    if (this.storageServiceValid === LOCAL_STORAGE_STATUS.Valid) {
      let products: Product[];
      const productsValue = this.storage.get(PRODUCT_LIST_KEY);
      if (productsValue) {
        products = JSON.parse(productsValue);
      }
      return products;
    }
  }

  clearLocalStorage() {
    if (this.storageServiceValid === LOCAL_STORAGE_STATUS.Valid) {
      this.storage.clear();
    }
  }
}
