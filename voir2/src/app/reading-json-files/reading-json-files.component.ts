import { Component, OnInit } from '@angular/core';
// @ts-ignore
import ProductListJson from '../../assets/voir_products.json';

@Component({
  selector: 'app-reading-json-files',
  templateUrl: './reading-json-files.component.html',
  styleUrls: ['./reading-json-files.component.css']
})
export class ReadingJsonFilesComponent implements OnInit {

  constructor() {
    console.log('Reading local json files');
    console.log(ProductListJson);
  }

  ngOnInit() {
  }

  getProductList() {
    console.log('getProductList');
    return ProductListJson;
  }


}
