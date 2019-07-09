export class Centro {

  codigo: number;
  localidad: string;
  generico: string;
  nombre: string;
  ultimaModificacion: Date;
  usuarioModificacion: string;

  constructor(json?: any) {
    if (json) {
      this.codigo = json.codigo;
      this.localidad = json.localidad;
      this.generico = json.generico;
      this.nombre = json.nombre;
      this.ultimaModificacion = json.ultimaModificacion;
      this.usuarioModificacion = json.usuarioModificacion;
    }
  }

  static fromJsonToArray(json: any): Centro[] {
    const centrosJson: [] = json.content;
    const centros: Centro[] = [];
    for (const centroJson of centrosJson) {
      centros.push(new Centro(centroJson));
    }
    return centros;
  }
}