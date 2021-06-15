import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {OrdersService} from '../../services/orders.service';
import {Order} from '../order';
import {saveAs} from 'file-saver';
import {CommonLanguageModel} from '../../common/common-language.model';
import {Pagination, PaginationRequest} from '../pagination';

@Injectable()
export class OrdersModel {
  private _orders: Order[] = [];
  private _currentOrder: Order = this._orders[0];
  private _pagination: Pagination | null = null;
  private errorLoading = false;

  public get order(): Order {
    return this._currentOrder;
  }

  public get orders(): Order[] {
    return this._orders;
  }

  public get pagination(): Pagination | null {
    return this._pagination;
  }

  constructor(private ordersService: OrdersService,
              private _snackBar: MatSnackBar,
              private _commonLanguageModel: CommonLanguageModel) {
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

  public loadOrdersPaginated(params?: PaginationRequest): void {
    this.ordersService.getOrdersPaginated(params).subscribe(
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
        this._pagination = result;
      },
      (err) => this.errorLoading = true);
  }

  public loadUncheckedOrders(): void {
    this.ordersService.getUncheckedOrders().subscribe(
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

  public markAsSeen(order: Order, loadSeen: boolean = false): void {
    this.ordersService.markAsSeen(order)
      .subscribe(() => {
        if (loadSeen) {
          this.loadUncheckedOrders();
        } else {
          this.pagination ?
            this.loadOrdersPaginated(new PaginationRequest(this.pagination.number, this.pagination.size)) :
            this.loadOrdersPaginated();
        }
        this._snackBar.open(`Order by ${order.user.username} marked as seen!`, 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
  }

  public download(order: Order): void {
    this.ordersService.download(order)
      .subscribe((response) => {
        const blob = new Blob([response], {type: 'application/octet-stream'});
        const fileName = `${this._commonLanguageModel.currentLanguage === 'en' ? 'Order' : 'Porudžbina'}-${order.id}-${order.user.username}.pdf`;
        saveAs(blob, fileName);
      });
  }

  public delete(order: Order, loadSeen: boolean = false): void {
    this.ordersService.delete(order)
      .subscribe(() => {
        if (loadSeen) {
          this.loadUncheckedOrders();
        } else {
          this.pagination ?
            this.loadOrdersPaginated(new PaginationRequest(this.pagination.number, this.pagination.size)) :
            this.loadOrdersPaginated();
        }
        this._snackBar.open(`Order by ${order.user.username} deleted!`, 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
  }

}
