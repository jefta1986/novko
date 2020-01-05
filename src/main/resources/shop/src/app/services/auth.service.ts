import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../app-constants';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _http: HttpClient, private _router: Router) { }

  authenticate(user: User): void {
    var response = null;
    this._http.get(AppConstants.baseUrl + 'login?username=' + user.getUsername + '&password=' + user.getPassword, { responseType: 'text' })
      .subscribe(res => {
        console.log(res);
        response = res;
      }, err => {
          LoginComponent.badLogin = true;
       }, () => {
        console.log('obradi response,setuj u localStorage,sad je kao admin dok se ne napravi da vraca rolu');
        AuthService.insertAdminInLocalStorage();

        console.log(AuthService.isAuthenticatedAdmin());
        if (AuthService.isAuthenticatedAdmin()) {
          this._router.navigate(['admin']);
        }
        else if (AuthService.isAuthenticatedUser()) {
          this._router.navigate(['home']);
        }
      });
  }

  logout(): void {
    var response = null;
    this._http.get(AppConstants.baseUrl + 'logout').subscribe(res => {
      console.log(res);
      response = res;
    }, err => { }, () => {
      console.log('obradi response,obrisi sve iz localStorage');
    });
  }

  static isAuthenticatedUser(): boolean {
    var isLoggedInUser = localStorage.getItem(AppConstants.isLoggedInUser);
    var role = this.getRole();
    if (isLoggedInUser == AppConstants.trueString && role == AppConstants.roleUser) {
      return true;
    }
    return false;
  }

  static isAuthenticatedAdmin(): boolean {
    var isLoggedInAdmin = localStorage.getItem(AppConstants.isLoggedInAdmin);
    var role = this.getRole();
    if (isLoggedInAdmin == AppConstants.trueString && role == AppConstants.roleAdmin) {
      return true;
    }
    return false;
  }

  static getRole(): string {
    return localStorage.getItem(AppConstants.role);
  }

  static emptyLocalStorage() {
    localStorage.removeItem(AppConstants.isLoggedInAdmin);
    localStorage.removeItem(AppConstants.isLoggedInUser);
    localStorage.removeItem(AppConstants.role);
  }

  static insertUserInLocalStorage() {
    localStorage.setItem(AppConstants.isLoggedInUser, AppConstants.trueString);
    localStorage.setItem(AppConstants.role, AppConstants.roleUser)
  }

  static insertAdminInLocalStorage() {
    localStorage.setItem(AppConstants.isLoggedInAdmin, AppConstants.trueString);
    localStorage.setItem(AppConstants.role, AppConstants.roleAdmin)
  }

}
