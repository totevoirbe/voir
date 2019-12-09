import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TarifOnScreenComponent } from './tarif-on-screen/tarif-on-screen.component';

const routes: Routes = [
  { path: 'screen/:pageSelector', component: TarifOnScreenComponent },
  { path: '', redirectTo: '/screen/P1', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class ScreenRoutingModule { }
