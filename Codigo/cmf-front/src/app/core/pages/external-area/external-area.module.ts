import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExternalAreaComponent } from '../../components/external-area/external-area.component';
import { ComponentsModule } from '../../components/components.module';

@NgModule({
  declarations: [ExternalAreaComponent],
  imports: [CommonModule, ComponentsModule],
  exports: [ExternalAreaComponent],
})
export class ExternalAreaModule {}
