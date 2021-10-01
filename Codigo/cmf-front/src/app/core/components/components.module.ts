import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterCardComponent } from './register-card/register-card.component';
import { IonicModule } from '@ionic/angular';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginCardComponent } from './login-card/login-card.component';
import { TopbarComponent } from './topbar/topbar.component';

@NgModule({
  declarations: [RegisterCardComponent, LoginCardComponent, TopbarComponent],
  imports: [CommonModule, IonicModule, ReactiveFormsModule],
  exports: [RegisterCardComponent, TopbarComponent],
})
export class ComponentsModule {}
