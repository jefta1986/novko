import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {AppConstants} from '../app-constants';
import {User} from '../data/user';
import {Router} from '@angular/router';
import {Observable, throwError} from 'rxjs';
import {catchError, map, retry, timeout} from 'rxjs/operators';
import {LoggedUser} from '../data/logged-user';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonLanguageModel} from '../common/common-language.model';
import {RegisterUser} from '../data/register-user';
import {LanguageType} from '../common/abstract-language.model';

@Injectable()
export class AuthService {
  public loginError = false;
  public loginTimestamp: Date | null = null;
  public user: LoggedUser | null = null;

  public get isAuthenticatedUser(): boolean | null {
    return this.user && this.user.role === AppConstants.roleUser;
  }

  public get isAuthenticatedAdmin(): boolean | null {
    return this.user && this.user.role === AppConstants.roleAdmin;
  }

  static emptyLocalStorage() {
    localStorage.removeItem(AppConstants.loginTimestamp);
    localStorage.removeItem(AppConstants.user);
  }

  private insertUserInLocalStorage(user: LoggedUser): void {
    localStorage.setItem(AppConstants.user, JSON.stringify(user));
    localStorage.setItem(AppConstants.loginTimestamp, new Date().getTime().toString());
  }

  constructor(private _http: HttpClient,
              private _router: Router,
              private _snackBar: MatSnackBar,
              private commonLanguageModel: CommonLanguageModel) {
    const storageTime = localStorage.getItem(AppConstants.loginTimestamp);
    const loggedUser = localStorage.getItem(AppConstants.user);
    if (storageTime) {
      this.loginTimestamp = new Date(Number(storageTime));
      // We check if the session has expired
      const sessionExpired = (new Date().getTime() - this.loginTimestamp.getTime() >= 1000 * 60 * 30);

      if (sessionExpired) {
        AuthService.emptyLocalStorage();
        this.user = null;
        this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSessionExpired || '', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
        // We set logged user if available in storage and session is not expired
      } else if (loggedUser) {
        this.user = JSON.parse(loggedUser);
      }
    }
  }

  public authenticate(user: User, redirect: boolean = true): Observable<any> {
    return this._http.post(
      `${AppConstants.baseUrl}login?username=${user.getUsername}&password=${user.getPassword}`,
      null,
      {responseType: 'text'}
    )
      .pipe(
        retry(0),
        timeout(25000),
        map(response => this.parseLogin(response, redirect)),
        catchError(this.handleLoginError)
      );
  }

  public register(user: RegisterUser, role: string) {
    this._http.post(`${AppConstants.baseUrl}registration?language=${user.language}&role=${role}`, user)
      .subscribe(res => {
      }, err => {
        this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }, () => {
        this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.userRegistered, ['username'], [user.username]), 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
  }

  public logout(redirect: boolean = true): void {
    this._http.get(AppConstants.baseUrl + 'logout').subscribe(res => {
      if (this.isAuthenticatedAdmin === true) {
        this._router.navigate(['/']);
      }
    }, err => {
      if (err.status === 401) {
        AuthService.emptyLocalStorage();
        this.user = null;
        if (redirect) {
          this._router.navigate(['/login']);
        }
      }
    }, () => {
      this.commonLanguageModel.changeLanguage('sr');
      AuthService.emptyLocalStorage();
      this.user = null;
      if (redirect) {
        this._router.navigate(['/login']);
      }
    });
  }

  private parseLogin(res: string, redirect: boolean): void {
    const user: LoggedUser = JSON.parse(res);
    this.user = user;

    this.insertUserInLocalStorage(user);
    this.commonLanguageModel.changeLanguage(user.language.toLowerCase() as LanguageType);

    if (user.role === AppConstants.roleAdmin && redirect) {
      this._router.navigate(['admin-unchecked-orders']);
    } else if (user.role === AppConstants.roleUser && redirect) {
      this._router.navigate(['/']);
    }
  }

  private handleLoginError(err: HttpErrorResponse): Observable<HttpErrorResponse> {
    this.loginError = true;
    return throwError(err);
  }
}
