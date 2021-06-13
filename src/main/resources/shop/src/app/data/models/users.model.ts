import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {UserService} from '../../services/user.service';
import {LoggedUser} from '../logged-user';
import {EditUser} from '../edit-user';

@Injectable()
export class UsersModel {
  private _users: LoggedUser[] = [];
  private _currentUser: LoggedUser = this._users[0];
  private errorLoading = false;

  public get product(): LoggedUser {
    return this._currentUser;
  }

  public get users(): LoggedUser[] {
    return this._users;
  }

  constructor(private userService: UserService,
              private _snackBar: MatSnackBar) {
    this.loadUsers();
  }

  public loadUsers(): void {
    this.userService.getAllUsers().subscribe(
      (result) => {
        this._users = result.map(({
                                    active,
                                    code,
                                    firma,
                                    grad,
                                    id,
                                    language,
                                    mb,
                                    pib,
                                    rabat,
                                    role,
                                    ulica,
                                    username
                                  }) => new LoggedUser(active,
          code,
          firma,
          grad,
          id,
          language,
          mb,
          pib,
          rabat,
          role,
          ulica,
          username));

      },
      (err) => this.errorLoading = true);
  }

  public changeActiveStatus(user: LoggedUser): void {
    this.userService.changeActiveStatus(user).subscribe(
      (result) => {
        this.loadUsers();
      },
      (err) => this.errorLoading = true);
  }

  public edit(user: EditUser): void {
    this.userService.editUser(user).subscribe(
      (result) => {
        this.loadUsers();
      },
      (err) => this.errorLoading = true);
  }

  public deleteUser(user: LoggedUser): void {
    this.userService.deleteUser(user).subscribe(
      (result) => {
        this.loadUsers();
      },
      (err) => this.errorLoading = true);
  }
}
