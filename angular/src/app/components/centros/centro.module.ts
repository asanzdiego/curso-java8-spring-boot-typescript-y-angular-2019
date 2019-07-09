import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CentroListComponent } from './centro-list.component';
import { CentroViewComponent } from './centro-view.component';
import { CentroEditComponent } from './centro-edit.component';
import { CentroNewComponent } from './centro-new.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';


@NgModule({
  declarations: [
    CentroListComponent,
    CentroViewComponent,
    CentroEditComponent,
    CentroNewComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule
  ]
})
export class CentroModule { }
