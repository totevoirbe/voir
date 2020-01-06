import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Page1OnScreenComponent } from './tarif-on-screen/page1-on-screen/page1-on-screen.component';
import { Page2OnScreenComponent } from './tarif-on-screen/page2-on-screen/page2-on-screen.component';
import { Page3OnScreenComponent } from './tarif-on-screen/page3-on-screen/page3-on-screen.component';
import { Page4OnScreenComponent } from './tarif-on-screen/page4-on-screen/page4-on-screen.component';
import { MenuAsBookOnWebComponent } from './tarif-on-web/menu-as-book-on-web/menu-as-book-on-web.component';
import { TarifOnWebComponent } from './tarif-on-web/tarif-on-web.component';

const screenRoutes: Routes = [
  // { path: 'screen/:pageSelector', component: TarifOnScreenComponent },
  { path: 'screen/P1', component: Page1OnScreenComponent },
  { path: 'screen/P2', component: Page2OnScreenComponent },
  { path: 'screen/P3', component: Page3OnScreenComponent },
  { path: 'screen/P4', component: Page4OnScreenComponent },

  { path: 'web/menu', component: TarifOnWebComponent },
  { path: 'web/menu2', component: MenuAsBookOnWebComponent },

  { path: '', redirectTo: 'web/menu', pathMatch: 'full' }
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
