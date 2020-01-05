import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../app-constants';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _http: HttpClient) { }

  authenticate(user: User): void {
    var response = null;
    this._http.post(AppConstants.baseUrl + 'login', user).subscribe(res => {
      console.log(res);
      response = res;
    }, err => { }, () => {
      console.log('obradi response,setuj u localStorage');
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
    if (isLoggedInUser && role == 'ROLE_USER') {
      return true;
    }
    return false;
  }

  static isAuthenticatedAdmin(): boolean {
    var isLoggedInAdmin = localStorage.getItem(AppConstants.isLoggedInAdmin);
    var role = this.getRole();
    if (isLoggedInAdmin && role == 'ROLE_ADMIN') {
      return true;
    }
    return false;
  }

  static getRole(): string {
    return localStorage.getItem(AppConstants.role);
  }

  static emptyLocalStorage(){
    localStorage.removeItem(AppConstants.isLoggedInAdmin);
    localStorage.removeItem(AppConstants.isLoggedInUser);
    localStorage.removeItem(AppConstants.role);
  }

}
