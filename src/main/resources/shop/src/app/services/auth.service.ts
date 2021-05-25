import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConstants} from '../app-constants';
import {User} from '../models/user';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';
import {Observable, Subscription, throwError} from 'rxjs';
import {catchError, map, retry, timeout} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public loginError = false;
  public loginTimestamp: Date = null;

  static isAuthenticatedUser(): boolean {
    const isLoggedInUser = localStorage.getItem(AppConstants.isLoggedInUser);
    const role = this.getRole();

    return isLoggedInUser === AppConstants.trueString && role === AppConstants.roleUser;
  }

  static isAuthenticatedAdmin(): boolean {
    const isLoggedInAdmin = localStorage.getItem(AppConstants.isLoggedInAdmin);
    const role = this.getRole();
    return isLoggedInAdmin === AppConstants.trueString && role === AppConstants.roleAdmin;
  }

  static getRole(): string {
    return localStorage.getItem(AppConstants.role);
  }

  static emptyLocalStorage() {
    localStorage.removeItem(AppConstants.isLoggedInAdmin);
    localStorage.removeItem(AppConstants.loginTimestamp);
    localStorage.removeItem(AppConstants.isLoggedInUser);
    localStorage.removeItem(AppConstants.role);
  }

  static insertUserInLocalStorage() {
    localStorage.setItem(AppConstants.isLoggedInUser, AppConstants.trueString);
    localStorage.setItem(AppConstants.role, AppConstants.roleUser);
  }

  static insertAdminInLocalStorage() {
    localStorage.setItem(AppConstants.isLoggedInAdmin, AppConstants.trueString);
    localStorage.setItem(AppConstants.role, AppConstants.roleAdmin);
  }


  constructor(private _http: HttpClient, private _router: Router, private _snackBar: MatSnackBar) {
    const storageTime = localStorage.getItem(AppConstants.loginTimestamp);
    if (storageTime) {
      this.loginTimestamp = new Date(Number(storageTime));
      // We check if the session cookie has expired
      const sessionExpired = (new Date().getTime() - this.loginTimestamp.getTime() >= 1000 * 60 * 30);

      if (sessionExpired) {
        AuthService.emptyLocalStorage();
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
        this._router.navigate(['/login']);
      }
    }, () => {
      AuthService.emptyLocalStorage();
      this._router.navigate(['/login']);
    });
  }

  private parseLogin(res): void {
    const response = JSON.parse(res);
    localStorage.setItem(AppConstants.loginTimestamp, new Date().getTime().toString());

    if (response.role === AppConstants.roleAdmin) {
      AuthService.insertAdminInLocalStorage();
    } else {
      AuthService.insertUserInLocalStorage();
    }

    if (response.role === AppConstants.roleAdmin) {
      this._router.navigate(['admin']);
    } else if (response.role === AppConstants.roleUser) {
      this._router.navigate(['home']);
    }
  }

  private handleLoginError(err): Observable<any> {
    this.loginError = true;
    return throwError(err);
  }

}
