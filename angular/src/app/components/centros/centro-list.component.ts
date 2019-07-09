import { Component, OnInit } from '@angular/core';
import { Centro } from '../../model/centro';
import { CentroService } from '../../services/centros.service';

@Component({
  selector: 'app-centro-list',
  templateUrl: './centro-list.component.html'
})
export class CentroListComponent implements OnInit {

  centros: Centro[] = [];

  constructor(private centroService: CentroService) { }

  ngOnInit(): void {
    this.centroService.getCentros().subscribe(centros => this.centros = centros);
  }

}
