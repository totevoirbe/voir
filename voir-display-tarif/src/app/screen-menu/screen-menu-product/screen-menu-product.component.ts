import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/data-layer/model/product';
import { ProductDaoService } from 'src/app/data-layer/product-dao.service';

@Component({
  selector: 'app-screen-menu-product',
  templateUrl: './screen-menu-product.component.html',
  styleUrls: ['./screen-menu-product.component.css']
})

export class ScreenMenuProductComponent implements OnInit {

  @Input() productCode: string;

  product: Product;

  constructor(private productDaoService: ProductDaoService) { }

  ngOnInit() {
    this.productDaoService.getProductByCode(this.productCode)
      .subscribe(product => this.product = product);
  }


}
