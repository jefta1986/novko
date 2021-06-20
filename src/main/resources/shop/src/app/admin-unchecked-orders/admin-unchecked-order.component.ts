import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {OrdersModel} from '../data/models/orders.model';
import {Order} from '../data/order';
import {AdditionalLinks} from '../data/additional-links';
import {NoItem} from '../common/common-language.interface';
import {ConfirmDialogComponent} from '../dialogs/confirm-dialog/confirm-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-admin-unchecked-order',
  templateUrl: './admin-unchecked-order.component.html',
  styleUrls: ['./admin-unchecked-order.component.css']
})
export class AdminUncheckedOrderComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get orders(): Order[] {
    return this.ordersModel.orders;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.allOrders, '/admin-orders'),
  ];

  public noItemType: NoItem = NoItem.orders;

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected ordersModel: OrdersModel,
              private _dialog: MatDialog) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.ordersModel.loadUncheckedOrders();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public markAsSeen(order: Order): void {
    const dialogRef = this._dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.ordersModel.markAsSeen(order, true);
      }
    });
  }

  public download(order: Order): void {
    this.ordersModel.download(order);
  }

  public delete(order: Order): void {
    const dialogRef = this._dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.ordersModel.delete(order, true);
      }
    });
  }

}
