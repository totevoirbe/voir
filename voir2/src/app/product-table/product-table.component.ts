import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Product } from '../data_models/product';
import { ProductsService } from 'src/business_layer/products.service';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})

export class ProductTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'mini', 'normal', 'geant', 'fitmini', 'fitnormal', 'fitgeant', 'code', 'label', 'ticketLabel', 'htmlKeyLabel', 'hiddenIndex', 'image', 'vatRateOnPlace', 'vatRateTakeAway', 'webDetail', 'afficheDetail', 'canMerge'];
  products: Product[];
  dataSource;

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor(private productService: ProductsService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(products => {
        this.products = products;
        this.products.forEach(product => this.populateHiddenIndex(product));
        this.dataSource = new MatTableDataSource(this.products);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      });

  }

  public populateHiddenIndex(product: Product) {
    product.hiddenIndex = '';
    if (product.categoryTags) {
      product.categoryTags.forEach(categoryTag => product.hiddenIndex += ' ' + categoryTag.tag);
    }
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
