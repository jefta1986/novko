import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {OrdersService} from '../../services/orders.service';
import {Order} from '../order';
import {UsersModel} from './users.model';

@Injectable()
export class OrdersModel {
  private _orders: Order[] = [];
  private _currentOrder: Order = this._orders[0];
  private errorLoading = false;

  public get order(): Order {
    return this._currentOrder;
  }

  public get orders(): Order[] {
    return this._orders;
  }

  constructor(private ordersService: OrdersService,
              private _snackBar: MatSnackBar) {
  }

  public loadOrdersUsername(username: object): void {
    this.ordersService.getOrdersUsername(username).subscribe(
      (result) => {
        this._orders = result.map(({
                                     user,
                                     id,
                                     orderDate,
                                     quantity,
                                     status,
                                     totalAmountDin,
                                     totalAmountEuro,
                                     carts,
                                   }) => new Order(
          user,
          id,
          orderDate,
          quantity,
          status,
          totalAmountDin,
          totalAmountEuro,
          carts
        ));

      },
      (err) => this.errorLoading = true);
  }

  public loadOrdersPaginated(): void {
    this.ordersService.getOrdersPaginated().subscribe(
      (result) => {
        this._orders = result.content.map(({
                                     user,
                                     id,
                                     orderDate,
                                     quantity,
                                     status,
                                     totalAmountDin,
                                     totalAmountEuro,
                                     carts,
                                   }) => new Order(
          user,
          id,
          orderDate,
          quantity,
          status,
          totalAmountDin,
          totalAmountEuro,
          carts
        ));
      },
      (err) => this.errorLoading = true);
  }
}
