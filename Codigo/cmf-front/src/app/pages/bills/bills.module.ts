import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BillsPageRoutingModule } from './bills-routing.module';

import { BillsPage } from './bills.page';
import { ComponentsModule } from 'src/app/core/components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BillsPageRoutingModule,
    ComponentsModule,
    ReactiveFormsModule
  ],
  declarations: [BillsPage]
})
export class BillsPageModule {}
