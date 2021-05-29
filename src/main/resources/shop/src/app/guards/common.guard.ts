import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommonGuard implements CanActivate {

  constructor(private _router: Router,
              private _authService: AuthService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this._authService.isAuthenticatedAdmin || this._authService.isAuthenticatedUser)) {
      AuthService.emptyLocalStorage();
      this._router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
