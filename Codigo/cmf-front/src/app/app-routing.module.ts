import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { HomePage } from './pages/home/home.page';

const routes: Routes = [
  // {
  //   path: '',
  //   loadChildren: () =>
  //     import('./tabs/tabs.module').then((m) => m.TabsPageModule),
  // },
  {
    path: '',
    loadChildren: () =>
      import('./pages/external-area/external-area.module').then(
        (m) => m.ExternalAreaPageModule
      ),
  },
  {
    path: 'bills',
    loadChildren: () => import('./pages/bills/bills.module').then( m => m.BillsPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
