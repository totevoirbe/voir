import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';

import { ProductService } from 'src/product-service/product.service';
import { Product } from 'src/product-service/model/product';
import { ProductCategoryTag } from 'src/product-service/model/product-category-tag';

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
    private productService: ProductService
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
    console.log('getProduct('+id+')');
    this.productService.getProduct(id).subscribe(product => { this.product = product; });
  }

  getProductCategoryTagAsoptions(): string {
    console.log('getProductCategoryTagAsoptions()');
    let productCategoryTagAsoptions = '';
    this.productService.getProductCategoryTags().subscribe(productCategoryCats => {
      productCategoryCats.forEach(productCategoryCat => {
        if (productCategoryTagAsoptions.length > 0) { productCategoryTagAsoptions += ','; }
        productCategoryTagAsoptions += productCategoryCat.code;
      });
    });
    console.log('[productCategoryTagAsoptions]' + productCategoryTagAsoptions);
    return productCategoryTagAsoptions;
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.productService.updateProduct(this.product)
      .subscribe(() => this.goBack());
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      if (!this.product.productCategoryTags) {
        this.product.productCategoryTags = new Array<ProductCategoryTag>();
      }
      //      this.product.productCategoryTags.push({ tag: value.trim() });
    }
    if (input) {
      input.value = '';
    }
  }

  remove(productCategoryTag: ProductCategoryTag): void {
    const index = this.product.productCategoryTags.indexOf(productCategoryTag);
    if (index >= 0) {
      this.product.productCategoryTags.splice(index, 1);
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
    const vatRateOnPlace = null;
    const vatRateTakeAway = null;
    const mini = 0;
    const normal = 0;
    const geant = 0;
    const fitmini = 0;
    const fitnormal = 0;
    const fitgeant = 0;
    const webDetail = '';
    const afficheDetail = '';
    const canMerge = false;
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
      canMerge
    );
    this.submitted = false;

    return product;
  }

  get diagnostic() { return JSON.stringify(this.product); }

}
