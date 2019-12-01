import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { Product } from '../data-layer/model/product';
import { ProductDaoService } from '../data-layer/product-dao.service';
import { ScreenMenuItem } from '../data-layer/model/screenMenuItem';
import { ScreenMenuDaoService } from '../data-layer/screen-menu-dao.service';

export interface ProductElement {
  position: number;
  image: string;
  label: string;
  normal: number;
  geant: number;
  afficheDetail: string;
}

/**
 * @title Basic use of `<table mat-table>`
 */

@Component({
  selector: 'app-tarif-on-screen',
  templateUrl: './tarif-on-screen.component.html',
  styleUrls: ['./tarif-on-screen.component.css']
})
export class TarifOnScreenComponent implements OnInit {

  screenMenuItems: ScreenMenuItem[];

  constructor(
    private screenMenuDaoService: ScreenMenuDaoService
  ) { }

  ngOnInit() {
    console.log('INIT product table');
    this.screenMenuDaoService.getScreenMenuList()
      .subscribe(screenMenuItems => {
        this.screenMenuItems = screenMenuItems; console.log(this.screenMenuItems);
      });
  }
}

