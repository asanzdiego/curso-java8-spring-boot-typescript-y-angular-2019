import { Component, OnInit, Input } from '@angular/core';
import { Centro } from '../../model/centro';
import { CentroService } from '../../services/centros.service';

@Component({
  selector: 'app-centro-new',
  templateUrl: './centro-new.component.html'
})
export class CentroNewComponent {

  centro = new Centro();

  constructor(private centroService: CentroService) { }

  onSubmit() {
    console.log('onSubmit', this.centro);
    this.centroService.addCentro(this.centro).subscribe(centro => this.centro = centro);
  }
}
