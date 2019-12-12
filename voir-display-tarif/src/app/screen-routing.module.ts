import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Page1OnScreenComponent } from './tarif-on-screen/page1-on-screen/page1-on-screen.component';
import { Page2OnScreenComponent } from './tarif-on-screen/page2-on-screen/page2-on-screen.component';
import { Page3OnScreenComponent } from './tarif-on-screen/page3-on-screen/page3-on-screen.component';
import { Page4OnScreenComponent } from './tarif-on-screen/page4-on-screen/page4-on-screen.component';

const routes: Routes = [
  // { path: 'screen/:pageSelector', component: TarifOnScreenComponent },
  { path: 'screen/P1', component: Page1OnScreenComponent },
  { path: 'screen/P2', component: Page2OnScreenComponent },
  { path: 'screen/P3', component: Page3OnScreenComponent },
  { path: 'screen/P4', component: Page4OnScreenComponent },
  { path: '', redirectTo: '/screen/P1', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class ScreenRoutingModule { }
