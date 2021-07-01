import {Injectable} from '@angular/core';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoggedUser} from '../data/logged-user';
import {EditUser} from '../data/edit-user';
import {UsersSort} from '../data/users.sort';
import {Pagination} from '../data/pagination';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) {
  }

  getAllUsers(): Observable<LoggedUser[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/users`);
  }

  getAllUsersPaginated(usersSort: UsersSort, searchTextParams: { emailPart: string, mbPart: string, pibPart: string }): Observable<Pagination> {
    const {
      page,
      size,
      sort,
      direction,
    } = usersSort;
    return this._http.get<any>(`${AppConstants.baseUrl}filtered?page=${page}&size=${size}&sort=${sort}&direction=${direction}${searchTextParams.emailPart === '' ? '' : ('&emailPart=' + searchTextParams.emailPart)}${searchTextParams.mbPart === '' ? '' : ('&mbPart=' + searchTextParams.mbPart)}${searchTextParams.pibPart === '' ? '' : ('&pibPart=' + searchTextParams.pibPart)}`);
  }

  editUser(user: EditUser): Observable<LoggedUser[]> {
    return this._http.patch<any>(`${AppConstants.baseUrl}rest/user/edit`, user);
  }

  deleteUser(user: LoggedUser): Observable<LoggedUser[]> {
    return this._http.delete<any>(`${AppConstants.baseUrl}rest/user/delete/${user.id}`);
  }

  changeActiveStatus(user: LoggedUser): Observable<LoggedUser> {
    return this._http.patch<any>(`${AppConstants.baseUrl}rest/user/active/${user.id}`, user);
  }

}
