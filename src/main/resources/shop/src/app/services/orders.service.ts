import {Injectable} from '@angular/core';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order} from '../data/order';
import {Pagination} from '../data/pagination';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private _http: HttpClient) {
  }

  getOrdersUsername(username: object): Observable<Order[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/user`, username);
  }

  getOrdersPaginated(): Observable<Pagination> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/user?direction=ASC&page=0&size=12&sort=NEWEST`);
  }

}
