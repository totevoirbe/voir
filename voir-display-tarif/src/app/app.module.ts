import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';

import { MatTabsModule } from '@angular/material/tabs';
import { MatDividerModule } from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ScreenRoutingModule } from './screen-routing.module';

import { AppComponent } from './app.component';
import { TarifOnWebComponent } from './tarif-on-web/tarif-on-web.component';
import { HomeTarifOnWebComponent } from './tarif-on-web/home-tarif-on-web/home-tarif-on-web.component';
import { APP_BASE_HREF } from '@angular/common';
import { WebMenuComponent } from './web-menu/web-menu.component';
import { TabsMenuComponent } from './web-menu/tabs-menu/tabs-menu.component';
import { TabsMenuHeaderComponent } from './web-menu/tabs-menu/tabs-menu-header/tabs-menu-header.component';
import { TabsMenuProductComponent } from './web-menu/tabs-menu/tabs-menu-product/tabs-menu-product.component';
import { ScreenMenuComponent } from './screen-menu/screen-menu.component';
import { ScreenMenuHeaderComponent } from './screen-menu/screen-menu-header/screen-menu-header.component';
import { ScreenMenuProductComponent } from './screen-menu/screen-menu-product/screen-menu-product.component';

@NgModule({
  declarations: [
    AppComponent,
    TarifOnWebComponent,
    MatTabsModule,
    MatIconModule,
    MatDividerModule,
    FlexLayoutModule,
    HomeTarifOnWebComponent,
    WebMenuComponent,
    TabsMenuComponent,
    TabsMenuHeaderComponent,
    TabsMenuProductComponent,
    ScreenMenuComponent,
    ScreenMenuHeaderComponent,
    ScreenMenuProductComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTabsModule,
    MatIconModule,
    MatDividerModule,
    FlexLayoutModule,
    ScreenRoutingModule
  ],
  providers: [{ provide: APP_BASE_HREF, useValue: '/voir-display-tarif/' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
