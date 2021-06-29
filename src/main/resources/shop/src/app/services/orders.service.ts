import {Injectable} from '@angular/core';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order} from '../data/order';
import {Pagination} from '../data/pagination';
import {OrdersSort} from '../data/orders.sort';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private _http: HttpClient) {
  }

  getOrdersUsername(username: string): Observable<Order[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/user?username=${username}`);
  }

  getOrdersPaginated(params: OrdersSort, searchInput: string | undefined): Observable<Pagination> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/orders/filtered?direction=${params.direction}&page=${params.page}&size=${params.size}&sort=${params.sort}${searchInput ? '&userPart=' + searchInput : ''}${params.status !== null ? '&status=' + params.status : ''}${params.fromDate && params.toDate ? `&fromDate=${params.fromDate.unix().valueOf()}&toDate=${params.toDate.unix().valueOf()}` : ''}`);
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
