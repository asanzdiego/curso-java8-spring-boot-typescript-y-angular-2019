import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CentroListComponent } from './components/centros/centro-list.component';
import { CentroEditComponent } from './components/centros/centro-edit.component';
import { CentroNewComponent } from './components/centros/centro-new.component';
import { NothingComponent } from './components/nothing.component';

const routes: Routes = [
  { path: '', component: NothingComponent },
  { path: 'centros', component: CentroListComponent },
  { path: 'centros/new', component: CentroNewComponent },
  { path: 'centros/:codigo', component: CentroEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
