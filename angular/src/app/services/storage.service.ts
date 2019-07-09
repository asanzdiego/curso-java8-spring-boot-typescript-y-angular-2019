import { Injectable } from '@angular/core';
import { Usuario } from '../model/usuario';

@Injectable({ providedIn: 'root' })
export class StorageService {

  private _usuario = 'usuario';
  private _authToken = 'authToken';

  getAuthToken(): string {
    return this._getFromLocalStorage(this._authToken);
  }

  getUsuario(): Usuario {
    return this._getFromLocalStorage(this._usuario);
  }

  setAuthToken(authToken: string) {
    this._setToLocalStorage(this._authToken, authToken);
  }

  setUsuario(usuario: Usuario) {
    this._setToLocalStorage(this._usuario, usuario);
  }

  private _getFromLocalStorage(collectionName: string) {
    const data = localStorage.getItem(collectionName);
    if (data) {
      return JSON.parse(data);
    }
    return [];
  };

  private _setToLocalStorage(collectionName: string, data: any) {
    localStorage.setItem(collectionName, JSON.stringify(data));
  };
}
