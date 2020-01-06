import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';

import { MatTabsModule } from '@angular/material/tabs';
import { MatDividerModule } from '@angular/material/divider';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ScreenRoutingModule } from './screen-routing.module';

import { AppComponent } from './app.component';
import { CarouselPhotoComponent } from './carousel-photo/carousel-photo.component';
import { TarifOnScreenComponent } from './tarif-on-screen/tarif-on-screen.component';
import { RowProductOnScreenComponent } from './tarif-on-screen/row-product-on-screen/row-product-on-screen.component';
import { Page1OnScreenComponent } from './tarif-on-screen/page1-on-screen/page1-on-screen.component';
import { Page2OnScreenComponent } from './tarif-on-screen/page2-on-screen/page2-on-screen.component';
import { Page3OnScreenComponent } from './tarif-on-screen/page3-on-screen/page3-on-screen.component';
import { Page4OnScreenComponent } from './tarif-on-screen/page4-on-screen/page4-on-screen.component';
import { RowProducttitleOnScreenComponent } from './tarif-on-screen/row-producttitle-on-screen/row-producttitle-on-screen.component';
import { TarifOnWebComponent } from './tarif-on-web/tarif-on-web.component';
import { MenuAsBookOnWebComponent } from './tarif-on-web/menu-as-book-on-web/menu-as-book-on-web.component';
import { ProductOnMenuAsBookOnWebComponent } from './tarif-on-web/menu-as-book-on-web/product-on-menu-as-book-on-web/product-on-menu-as-book-on-web.component';
import { HeaderOnMenuAsBookOnWebComponent } from './tarif-on-web/menu-as-book-on-web/header-on-menu-as-book-on-web/header-on-menu-as-book-on-web.component';
import { HomeTarifOnWebComponent } from './tarif-on-web/home-tarif-on-web/home-tarif-on-web.component';
import { APP_BASE_HREF } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    CarouselPhotoComponent,
    TarifOnScreenComponent,
    RowProductOnScreenComponent,
    Page1OnScreenComponent,
    Page2OnScreenComponent,
    Page3OnScreenComponent,
    Page4OnScreenComponent,
    RowProducttitleOnScreenComponent,
    TarifOnWebComponent,
    MenuAsBookOnWebComponent,
    ProductOnMenuAsBookOnWebComponent,
    HeaderOnMenuAsBookOnWebComponent,
    MatTabsModule,
    MatDividerModule,
    FlexLayoutModule,
    HomeTarifOnWebComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTabsModule,
    MatDividerModule,
    FlexLayoutModule,
    ScreenRoutingModule
  ],
  providers: [{ provide: APP_BASE_HREF, useValue: '/voir-display-tarif/' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
