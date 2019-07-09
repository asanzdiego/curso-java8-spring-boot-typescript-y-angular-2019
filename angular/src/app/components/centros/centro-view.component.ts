import { Component, OnInit, Input } from '@angular/core';
import { Centro } from '../../model/centro';
import { CentroService } from '../../services/centros.service';

@Component({
  selector: 'app-centro-view',
  templateUrl: './centro-view.component.html'
})
export class CentroViewComponent {

  @Input() centro: Centro;

  constructor(private centroService: CentroService) {
    
  }
}
