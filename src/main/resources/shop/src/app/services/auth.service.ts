import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {AppConstants} from '../app-constants';
import {User} from '../models/user';
import {Router} from '@angular/router';
import {Observable, throwError} from 'rxjs';
import {catchError, map, retry, timeout} from 'rxjs/operators';
import {LoggedUser} from '../models/logged-user';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
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

  public authenticate(user: User, redirect: boolean = true): Observable<any> {
    return this._http.post(
      AppConstants.baseUrl + `login?username=${user.getUsername}&password=${user.getPassword}`,
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

  public logout(redirect: boolean = true): void {
    this._http.get(AppConstants.baseUrl + 'logout').subscribe(res => {
      if (this.isAuthenticatedAdmin === true) {
        this._router.navigate(['/home']);
      }
    }, err => {
      if (err.status === 401) {
        AuthService.emptyLocalStorage();
        this.user = null;
        if (redirect === true) {
          this._router.navigate(['/login']);
        }
      }
    }, () => {
      AuthService.emptyLocalStorage();
      this.user = null;
      if (redirect === true) {
        this._router.navigate(['/login']);
      }
    });
  }

  private parseLogin(res: string, redirect: boolean): void {
    const user: LoggedUser = JSON.parse(res);
    this.user = user;

    this.insertUserInLocalStorage(user);

    if (user.role === AppConstants.roleAdmin && redirect === true) {
      this._router.navigate(['admin']);
    } else if (user.role === AppConstants.roleUser && redirect === true) {
      this._router.navigate(['home']);
    }
  }

  private handleLoginError(err: HttpErrorResponse): Observable<HttpErrorResponse> {
    this.loginError = true;
    return throwError(err);
  }
}
