import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterCardComponent } from './register-card/register-card.component';
import { IonicModule } from '@ionic/angular';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [RegisterCardComponent],
  imports: [CommonModule, IonicModule, ReactiveFormsModule],
  exports: [RegisterCardComponent],
})
export class ComponentsModule {}
