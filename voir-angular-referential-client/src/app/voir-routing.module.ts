import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductTableComponent } from './product-table/product-table.component';
import { ProductFormComponent } from './product-form/product-form.component';

const routes: Routes = [
  { path: 'productTable', component: ProductTableComponent },
  { path: 'productForm/:id', component: ProductFormComponent },
  { path: '', redirectTo: '/productTable', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class VoirRoutingModule {}
