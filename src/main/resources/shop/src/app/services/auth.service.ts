import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConstants} from '../app-constants';
import {User} from '../models/user';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';
import {Observable, throwError} from 'rxjs';
import {catchError, map, retry, timeout} from 'rxjs/operators';
import {LoggedUser} from '../models/logged-user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public loginError = false;
  public loginTimestamp: Date = null;
  public user: LoggedUser = null;

  public get isAuthenticatedUser(): boolean {
    return this.user && this.user.role === AppConstants.roleUser;
  }

  public get isAuthenticatedAdmin(): boolean {
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
              private _snackBar: MatSnackBar) {
    const storageTime = localStorage.getItem(AppConstants.loginTimestamp);
    const loggedUser = localStorage.getItem(AppConstants.user);
    if (storageTime) {
      this.loginTimestamp = new Date(Number(storageTime));
      // We check if the session cookie has expired
      const sessionExpired = (new Date().getTime() - this.loginTimestamp.getTime() >= 1000 * 60 * 30);

      if (sessionExpired) {
        AuthService.emptyLocalStorage();
        this.user = null;
        this._snackBar.open('Your session has expired, please login again to continue shopping with your username!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
        // We set logged user if available in storage and session is not expired
      } else if (loggedUser) {
        this.user = JSON.parse(loggedUser);
      }
    }
  }

  public authenticate(user: User): Observable<any> {
    return this._http.post(
      AppConstants.baseUrl + 'login?username=' + user.getUsername + '&password=' + user.getPassword,
      null,
      {responseType: 'text'}
    )
      .pipe(
        retry(0),
        timeout(25000),
        map(response => this.parseLogin(response)),
        catchError(this.handleLoginError)
      );
  }

  public register(user: User, role: string) {
    this._http.post(AppConstants.baseUrl + 'registration?role=' + role, user, {responseType: 'text'})
      .subscribe(res => {
      }, err => {
        this._snackBar.open('Something went wrong,try again!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }, () => {
        this._snackBar.open('User registered!', 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
  }

  public logout(): void {
    this._http.get(AppConstants.baseUrl + 'logout').subscribe(res => {
    }, err => {
      if (err.status === 401) {
        AuthService.emptyLocalStorage();
        this.user = null;
        this._router.navigate(['/login']);
      }
    }, () => {
      AuthService.emptyLocalStorage();
      this.user = null;
      this._router.navigate(['/login']);
    });
  }

  private parseLogin(res): void {
    const user: LoggedUser = JSON.parse(res);
    this.user = user;

    this.insertUserInLocalStorage(user);

    if (user.role === AppConstants.roleAdmin) {
      this._router.navigate(['admin']);
    } else if (user.role === AppConstants.roleUser) {
      this._router.navigate(['home']);
    }
  }

  private handleLoginError(err): Observable<any> {
    this.loginError = true;
    return throwError(err);
  }

}
