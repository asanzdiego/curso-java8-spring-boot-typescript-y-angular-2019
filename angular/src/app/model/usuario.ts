export class Usuario {

  username: string;
  roles: string[];

  constructor(json?: any) {
    if (json) {
      this.username = json.username;
      this.roles = json.roles;
    }
  }
}
