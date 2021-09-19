import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterCardComponent } from './register-card/register-card.component';
import { IonicModule } from '@ionic/angular';

@NgModule({
  declarations: [RegisterCardComponent],
  imports: [CommonModule, IonicModule],
  exports: [RegisterCardComponent],
})
export class ComponentsModule {}
