export class User {

  private username;
  private password;

  constructor() {
  }

  public get getUsername() {
    return this.username;
  }

  public get getPassword() {
    return this.password;
  }

  public set setUsername(username) {
    this.username = username;
  }

  public set setPassword(password) {
    this.password = password;
  }
}
