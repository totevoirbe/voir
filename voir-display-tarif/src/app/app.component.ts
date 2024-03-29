import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { ProductDaoService } from './data-layer/product-dao.service';
import { LocalStorageService, LOCAL_STORAGE_STATUS } from './data-layer/local-storage.service';
import { Product } from './data-layer/model/product';

const enum DATA_ORIGIN {
  Default = 'DEFAULT',
  Local = 'LOCAL',
  Server = 'SERVER'
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AppComponent implements OnInit {

  dataOrigin: DATA_ORIGIN;
  localStorageStatus: LOCAL_STORAGE_STATUS;

  title = 'voir-angular-referential-client';

  constructor(
    private productDaoService: ProductDaoService,
    private localStorageService: LocalStorageService
  ) {
  }

  ngOnInit(): void {
    this.localStorageStatus = this.localStorageService.isStorageServiceValid();
    this.selectDefaultProduct();
  }

  selectDefaultProduct(): void {
    this.productDaoService.getDefaultProductList().subscribe(defaultProducts => {
      console.log('get Default products:');
      console.log(defaultProducts);
      this.productDaoService.setProducts(defaultProducts);
      this.dataOrigin = DATA_ORIGIN.Default;
    });
  }

  selectProduct(): void {
    this.productDaoService.getProducts()
      .subscribe(
        productList => {
          console.log('Products from server on app init:');
          console.log(productList);
          this.dataOrigin = DATA_ORIGIN.Server;
          if (!productList || productList.length <= 0) {
            console.log('Not found on server : Products from local storage on app init:');
            const products: Product[] = this.localStorageService.getProducts();
            if (products && products.length > 0) {
              console.log(products);
              this.productDaoService.setProducts(products);
              this.dataOrigin = DATA_ORIGIN.Local;
            } else {
              this.selectDefaultProduct();
            }
          }
        },
        err => {
          console.log('Products read server error:' + err);
          const products: Product[] = this.localStorageService.getProducts();
          if (products && products.length > 0) {
            console.log(products);
            this.productDaoService.setProducts(products);
            this.dataOrigin = DATA_ORIGIN.Local;
          } else {
            this.productDaoService.getDefaultProductList().subscribe(defaultProducts => {
              console.log('get Default products:');
              console.log(defaultProducts);
              this.productDaoService.setProducts(defaultProducts);
              this.dataOrigin = DATA_ORIGIN.Default;
            });
          }
        }
      );
  }
}
