import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { ReadingJsonFilesComponent } from './reading-json-files/reading-json-files.component';
import { ProductTableComponent } from './product-table/product-table.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';


const routes: Routes = [
  { path: 'productList', component: ProductListComponent },
  { path: 'productTable', component: ProductTableComponent },
  { path: 'detail/:id', component: ProductFormComponent },
  { path: '', redirectTo: '/productTable', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent }
];


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class VoirRoutingModule { }
