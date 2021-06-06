import {Injectable} from '@angular/core';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoggedUser} from '../data/logged-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) {
  }

  getAllUsers(): Observable<LoggedUser[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/users`);
  }

}
