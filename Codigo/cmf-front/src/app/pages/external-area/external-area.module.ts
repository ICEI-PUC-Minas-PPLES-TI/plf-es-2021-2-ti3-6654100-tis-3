import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ExternalAreaPageRoutingModule } from './external-area-routing.module';

import { ExternalAreaPage } from './external-area.page';
import { ComponentsModule } from 'src/app/core/components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ExternalAreaPageRoutingModule,
    ComponentsModule
  ],
  declarations: [ExternalAreaPage]
})
export class ExternalAreaPageModule {}
