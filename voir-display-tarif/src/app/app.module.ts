import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LearnComponent } from './learn/learn.component';
import { TextRollComponent } from './text-roll/text-roll.component';
import { CarouselPhotoComponent } from './carousel-photo/carousel-photo.component';
import { TarifOnScreenComponent } from './tarif-on-screen/tarif-on-screen.component';
import { FilterPagePipe } from './tarif-on-screen/filter-page.pipe';
import { ScreenRoutingModule } from './screen-routing.module';
import { RowProductOnScreenComponent } from './tarif-on-screen/row-product-on-screen/row-product-on-screen.component';
import { Page1OnScreenComponent } from './tarif-on-screen/page1-on-screen/page1-on-screen.component';
import { Page2OnScreenComponent } from './tarif-on-screen/page2-on-screen/page2-on-screen.component';
import { Page3OnScreenComponent } from './tarif-on-screen/page3-on-screen/page3-on-screen.component';
import { Page4OnScreenComponent } from './tarif-on-screen/page4-on-screen/page4-on-screen.component';
import { RowProducttitleOnScreenComponent } from './tarif-on-screen/row-producttitle-on-screen/row-producttitle-on-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    LearnComponent,
    TextRollComponent,
    CarouselPhotoComponent,
    TarifOnScreenComponent,
    FilterPagePipe,
    RowProductOnScreenComponent,
    Page1OnScreenComponent,
    Page2OnScreenComponent,
    Page3OnScreenComponent,
    Page4OnScreenComponent,
    RowProducttitleOnScreenComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ScreenRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
