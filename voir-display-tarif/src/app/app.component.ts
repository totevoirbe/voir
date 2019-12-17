import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { ProductDaoService } from './data-layer/product-dao.service';
import { LocalStorageService } from './data-layer/local-storage.service';
import { Product } from './data-layer/model/product';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AppComponent implements OnInit {

  title = 'voir-angular-referential-client';

  images = [
    '../../assets/imgSalades/salade-cesar.jpg',
    '../../assets/imgSalades/salade-italienne.jpg',
    '../../assets/imgSalades/27433644-fresh-vegetable-salad-in-a-plastic-take-away-bowl.jpg',
    '../../assets/imgSalades/58978038-salad-of-takeaway-container-on-white-background.jpg',
    '../../assets/imgSalades/fresh-greek-salad-plastic-package-to-take-away-lunch-white-marble-background-fresh-greek-salad-plastic-package-to-153833376.jpg',
  ];

  constructor(
    private productDaoService: ProductDaoService,
    private localStorageService: LocalStorageService
  ) { }

  ngOnInit(): void {
    this.productDaoService.getProducts()
      .subscribe(
        productList => {
          console.log('Products from server on app init:');
          console.log(productList);
          if (!productList || productList.length <= 0) {
            console.log('Products from local storage on app init:');
            const products: Product[] = this.localStorageService.getProducts();
            console.log(products);
            this.productDaoService.setProducts(products);
          }
        },
        err => {
          console.log('Products read server error:' + err);
          console.log('Products from local storage on app init:');
          const products: Product[] = this.localStorageService.getProducts();
          console.log(products);
          this.productDaoService.setProducts(products);
        }
      );
  }
}
