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

@NgModule({
  declarations: [
    AppComponent,
    LearnComponent,
    TextRollComponent,
    CarouselPhotoComponent,
    TarifOnScreenComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
