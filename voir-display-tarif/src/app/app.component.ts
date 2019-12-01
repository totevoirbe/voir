import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Product } from './data-layer/model/product';
import { ProductDaoService } from './data-layer/product-dao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class AppComponent {

  index;

  images = [
    '../../assets/imgSalades/salade-cesar.jpg',
    '../../assets/imgSalades/salade-italienne.jpg',
    '../../assets/imgSalades/27433644-fresh-vegetable-salad-in-a-plastic-take-away-bowl.jpg',
    '../../assets/imgSalades/58978038-salad-of-takeaway-container-on-white-background.jpg',
    '../../assets/imgSalades/fresh-greek-salad-plastic-package-to-take-away-lunch-white-marble-background-fresh-greek-salad-plastic-package-to-153833376.jpg',
  ];

  onChange(idx) {
    console.log(idx);
    this.index = idx;
  }

}
