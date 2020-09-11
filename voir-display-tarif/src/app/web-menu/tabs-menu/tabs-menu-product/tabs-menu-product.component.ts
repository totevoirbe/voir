import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/data-layer/model/product';
import { ProductDaoService } from 'src/app/data-layer/product-dao.service';

@Component({
  selector: 'app-tabs-menu-product',
  templateUrl: './tabs-menu-product.component.html',
  styleUrls: ['./tabs-menu-product.component.css']
})
export class TabsMenuProductComponent implements OnInit {

  @Input() productCode: string;

  product: Product;

  constructor(private productDaoService: ProductDaoService) { }

  ngOnInit() {
    this.productDaoService.getProductByCode(this.productCode)
      .subscribe(product => this.product = product);
  }


}
