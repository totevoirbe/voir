import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, MatSortModule, MatInputModule } from '@angular/material';
import { MatFormFieldModule, MatChipsModule, MatIconModule, MatTabsModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { ProductTableComponent } from './product-table/product-table.component';
import { MessageComponent } from './message/message.component';
import { VoirRoutingModule } from './voir-routing.module';
import { ProductFormComponent } from './product-form/product-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductTableComponent,
    MessageComponent,
    ProductFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    VoirRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    MatIconModule,
    MatTabsModule,
    BrowserAnimationsModule
  ],
  exports: [
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    MatIconModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
