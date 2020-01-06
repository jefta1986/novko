import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../app-constants';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _http: HttpClient, private _router: Router,private _snackBar: MatSnackBar) { }

  authenticate(user: User): void {
    
    var responseRole = '';
    this._http.get(AppConstants.baseUrl + 'login?username=' + user.getUsername + '&password=' + user.getPassword, { responseType: 'text'})
      .subscribe((res:any) => {
        responseRole = res;
      }, err => {
        LoginComponent.badLogin = true;
      }, () => {
        if (responseRole == AppConstants.roleAdmin) {
          AuthService.insertAdminInLocalStorage();
        }
        else {
          AuthService.insertUserInLocalStorage();
        }

        if (AuthService.isAuthenticatedAdmin()) {
          this._router.navigate(['admin']);
        }
        else if (AuthService.isAuthenticatedUser()) {
          this._router.navigate(['home']);
        }
      });
  }

  register(user:User,role:string){
    this._http.post(AppConstants.baseUrl + 'registration?role=' + role,user,{responseType:'text'})
              .subscribe(res=>{},err=>{
                this._snackBar.open("Something went wrong,try again!", 'Error', {
                  duration: 4000,
                  panelClass: ['my-snack-bar-error']
                });
              },()=>{
                this._snackBar.open("User registrated!", 'Success', {
                  duration: 4000,
                  panelClass: ['my-snack-bar']
                });
              });
  }

  logout(): void {
    var response = null;
    this._http.get(AppConstants.baseUrl + 'logout').subscribe(res => {
      console.log(res)
     }, err => { 
      console.log(err)
    }, () => {
      AuthService.emptyLocalStorage();
      this._router.navigate(["/login"]);
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
