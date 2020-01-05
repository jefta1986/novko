import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { AppConstants } from './app-constants';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private _router: Router) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        return next.handle(request).pipe(tap(() => { },
            (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status !== 401 && request.url.includes('login')) {
                        return;
                    }

                    this._router.navigate(['login']);
                    AuthService.emptyLocalStorage();
                }
            },()=>{}));
    }
}