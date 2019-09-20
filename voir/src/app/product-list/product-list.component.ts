import { Component, OnInit } from '@angular/core';
import { Product } from '../data_models/product';
import { ProductsService } from 'src/business_layer/products.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

export class ProductListComponent implements OnInit {

  selectedProduct: Product;
  products: Product[];

  constructor(private productService: ProductsService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }


  onSelect(product: Product): void {
    this.selectedProduct = product;
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.productService.addProduct({ name } as Product)
      .subscribe(product => {
        this.products.push(product);
      });
  }

  delete(product: Product): void {
    this.products = this.products.filter(p => p !== product);
    this.productService.deleteProduct(product).subscribe();
  }

}
