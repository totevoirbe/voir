import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/data-layer/model/product';
import { ProductDaoService } from 'src/app/data-layer/product-dao.service';

@Component({
  selector: 'app-row-product-on-screen',
  templateUrl: './row-product-on-screen.component.html',
  styleUrls: ['./row-product-on-screen.component.css']
})
export class RowProductOnScreenComponent implements OnInit {

  @Input() productCode: string;

  product: Product;

  constructor(private productDaoService: ProductDaoService) { }

  ngOnInit() {
    this.productDaoService.getProductByCode(this.productCode)
      .subscribe(product => this.product = product);
  }
}
