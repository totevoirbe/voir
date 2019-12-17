import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/data-layer/model/product';
import { ProductDaoService } from 'src/app/data-layer/product-dao.service';

@Component({
  selector: 'app-product-on-menu-as-book-on-web',
  templateUrl: './product-on-menu-as-book-on-web.component.html',
  styleUrls: ['./product-on-menu-as-book-on-web.component.css']
})
export class ProductOnMenuAsBookOnWebComponent implements OnInit {

  @Input() productCode: string;

  product: Product;

  constructor(private productDaoService: ProductDaoService) { }

  ngOnInit() {
    this.productDaoService.getProductByCode(this.productCode)
      .subscribe(product => this.product = product);
  }


}
