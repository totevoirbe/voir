import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { ProductService } from 'src/product-service/product.service';
import { ProductTable } from './product-table';
import { Product } from 'src/product-service/model/product';


@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})

export class ProductTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'mini', 'normal', 'geant', 'fitmini', 'fitnormal', 'fitgeant', 'code', 'label', 'ticketLabel', 'htmlKeyLabel', 'productCategoryTagsAsString', 'image', 'vatRateOnPlaceAsString', 'vatRateTakeAwayAsString', 'webDetail', 'afficheDetail', 'canMerge'];
  productTables: ProductTable[] = new Array();
  dataSource;

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    console.log('INIT product table');
    this.productService.getProducts()
      .subscribe(products => {
        products.forEach(product => this.populate(product));
        this.dataSource = new MatTableDataSource(this.productTables);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      });

  }

  public populate(product: Product) {
    const productTable = new ProductTable(
      product.id,
      product.label,
      product.ticketLabel,
      product.code,
      product.name,
      product.htmlKeyLabel,
      product.productCategoryTags,
      product.image,
      product.vatRateOnPlace,
      product.vatRateTakeAway,
      product.mini,
      product.normal,
      product.geant,
      product.fitmini,
      product.fitnormal,
      product.fitgeant,
      product.webDetail,
      product.afficheDetail,
      product.canMerge);

    productTable.productCategoryTagsAsString = '';
    if (product.productCategoryTags) {
      product.productCategoryTags.forEach(productCategoryTag => {
        if (productTable.productCategoryTagsAsString.length > 0) { productTable.productCategoryTagsAsString += ','; }
        productTable.productCategoryTagsAsString += productCategoryTag.label;
      });
    }
    if (product.vatRateOnPlace) {
      productTable.vatRateOnPlaceAsString = '' + product.vatRateOnPlace.label + ' ' + product.vatRateOnPlace.rate + '%';
    }
    if (product.vatRateTakeAway) {
      productTable.vatRateTakeAwayAsString = '' + product.vatRateTakeAway.label + ' ' + product.vatRateTakeAway.rate + '%';
    }
    console.log(productTable);
    this.productTables.push(productTable);
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
