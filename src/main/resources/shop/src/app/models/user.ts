export class User {

  private username: string;
  private password: string;

  constructor(username: string,
              password: string) {
    this.username = username;
    this.password = password;
  }

  public get getUsername() {
    return this.username;
  }

  public get getPassword() {
    return this.password;
  }

  public set setUsername(username: string) {
    this.username = username;
  }

  public set setPassword(password: string) {
    this.password = password;
  }
}
