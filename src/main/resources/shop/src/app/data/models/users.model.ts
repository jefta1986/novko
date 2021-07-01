import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {UserService} from '../../services/user.service';
import {LoggedUser} from '../logged-user';
import {EditUser} from '../edit-user';
import {Pagination} from '../pagination';
import {ProductsSort} from '../products.sort';
import {UsersSort} from '../users.sort';
import {CommonLanguageModel} from '../../common/common-language.model';

@Injectable()
export class UsersModel {
  private _users: LoggedUser[] = [];
  private _pagination: Pagination | null = null;
  private _currentUser: LoggedUser = this._users[0];
  private usersSort: UsersSort | null = null;
  private errorLoading = false;

  public get product(): LoggedUser {
    return this._currentUser;
  }

  public get users(): LoggedUser[] {
    return this._users;
  }

  public get pagination(): Pagination | null {
    return this._pagination;
  }

  constructor(private userService: UserService,
              private _snackBar: MatSnackBar,
              protected commonLanguageModel: CommonLanguageModel) {
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

  public loadUsersPaginated(usersSort: UsersSort, searchInputEmail : string, searchInputMb: string, searchInputPib: string): void {
    this.usersSort = usersSort;
    const searchTextParams = {
      emailPart: searchInputEmail || '',
      mbPart: searchInputMb || '',
      pibPart: searchInputPib || ''
    };
    this.userService.getAllUsersPaginated(usersSort, searchTextParams).subscribe(
      (result) => {
        this._users = result.content.map(({
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
        this._pagination = result;
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
