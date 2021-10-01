import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginCardComponent } from 'src/app/core/components/login-card/login-card.component';
import { RegisterCardComponent } from 'src/app/core/components/register-card/register-card.component';

import { ExternalAreaPage } from './external-area.page';

const routes: Routes = [
  {
    path: 'external',
    component: ExternalAreaPage,
    children: [
      {
        path: 'login',
        component: LoginCardComponent,
      },
      {
        path: 'register',
        component: RegisterCardComponent,
      },
    ]
  },
  {
    path: '',
    redirectTo: '/external/login',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExternalAreaPageRoutingModule {}
