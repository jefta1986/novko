import {Injectable} from '@angular/core';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order} from '../data/order';
import {Pagination, PaginationRequest} from '../data/pagination';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private _http: HttpClient) {
  }

  getOrdersUsername(username: object): Observable<Order[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/user`, username);
  }

  getOrdersPaginated(params: PaginationRequest = {page: 0, size: 12}): Observable<Pagination> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/filtered?direction=ASC&page=${params.page}&size=${params.size}&sort=NEWEST`);
  }

  getUncheckedOrders(): Observable<Order[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/unchecked`);
  }

  markAsSeen(order: Order): Observable<any> {
    return this._http.patch<any>(`${AppConstants.baseUrl}rest/orders/unchecked?id=${order.id}`, {id: order.id});
  }

  download(order: Order): Observable<any> {
    return this._http.get(`${AppConstants.baseUrl}rest/reports/download/${order.id}`,{ responseType: 'blob'});
  }

  delete(order: Order): Observable<any> {
    return this._http.delete<any>(`${AppConstants.baseUrl}rest/orders?id=${order.id}`);
  }


}
