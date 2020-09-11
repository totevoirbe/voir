import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WebMenuComponent } from './web-menu/web-menu.component';
import { ScreenMenuComponent } from './screen-menu/screen-menu.component';

const screenRoutes: Routes = [
  // { path: 'screen/:pageSelector', component: TarifOnScreenComponent },
  { path: 'web/menu', component: WebMenuComponent },
  { path: 'screen/menu', component: ScreenMenuComponent },
  { path: '', redirectTo: 'screen/menu', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(
      screenRoutes,
      { enableTracing: true }
    ),
  ],
  exports: [RouterModule]
})

export class ScreenRoutingModule { }
