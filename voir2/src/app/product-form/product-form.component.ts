import { Component, OnInit } from '@angular/core';

import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';


import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ProductsService } from 'src/business_layer/products.service';
import { Product, ProductCategoryTag } from '../data_models/product';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  product: Product;
  submitted = false;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private productsService: ProductsService
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  onSubmit() {
    this.save();
    this.submitted = true;
  }

  getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.productsService.getProduct(id).subscribe(product => { this.product = product; });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.productsService.updateProduct(this.product)
      .subscribe(() => this.goBack());
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      if (!this.product.categoryTags) {
        this.product.categoryTags = new Array<ProductCategoryTag>();
      }

      this.product.categoryTags.push({ tag: value.trim() });
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(productCategoryTag: ProductCategoryTag): void {
    const index = this.product.categoryTags.indexOf(productCategoryTag);
    if (index >= 0) {
      this.product.categoryTags.splice(index, 1);
    }
  }

  getNewProduct(): Product {
    const id = 0;
    const label = '';
    const ticketLabel = '';
    const code = '';
    const name = '';
    const htmlKeyLabel = '';
    const categoryTags: ProductCategoryTag[] = [];
    const image = '';
    const vatRateOnPlace = 0;
    const vatRateTakeAway = 0;
    const mini = 0;
    const normal = 0;
    const geant = 0;
    const fitmini = 0;
    const fitnormal = 0;
    const fitgeant = 0;
    const webDetail = '';
    const afficheDetail = '';
    const canMerge = false;
    const hiddenIndex = '';
    const product = new Product(
      id,
      label,
      ticketLabel,
      code,
      name,
      htmlKeyLabel,
      categoryTags,
      image,
      vatRateOnPlace,
      vatRateTakeAway,
      mini,
      normal,
      geant,
      fitmini,
      fitnormal,
      fitgeant,
      webDetail,
      afficheDetail,
      canMerge,
      hiddenIndex
    );
    this.submitted = false;

    return product;
  }

  get diagnostic() { return JSON.stringify(this.product); }

}
