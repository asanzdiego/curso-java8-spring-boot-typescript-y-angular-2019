import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { Usuario } from 'src/app/model/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  usuario: Usuario;

  username: string;
  password: string;

  constructor(
    private loginService: LoginService,
    private router: Router) {

  }

  login() {
    this.loginService.login(this.username, this.password).subscribe(usuario => {
      this.usuario = usuario;
      this.router.navigate(['/centros']);
    });
  }


  logout() {
    console.log('logout');
    this.loginService.logout().subscribe(() => {
      this.usuario = null;
    });
  }
}
