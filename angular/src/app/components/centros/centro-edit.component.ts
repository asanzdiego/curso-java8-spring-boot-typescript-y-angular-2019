import { Component, OnInit, Input } from '@angular/core';
import { Centro } from '../../model/centro';
import { CentroService } from '../../services/centros.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-centro-edit',
  templateUrl: './centro-edit.component.html'
})
export class CentroEditComponent implements OnInit {

  codigo: number;
  centro = new Centro();

  constructor(
    private centroService: CentroService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

    this.codigo = activatedRoute.snapshot.params['codigo'];
  }

  ngOnInit(): void {
    this.centroService.getCentro(this.codigo).subscribe(centro => this.centro = centro);
  }

  onSubmit() {
    console.log('onSubmit', this.centro);
    this.centroService.updateCentro(this.centro).subscribe(centro => this.centro = centro);
  }
}
