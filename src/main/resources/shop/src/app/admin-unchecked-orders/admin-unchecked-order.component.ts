import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {OrdersModel} from '../data/models/orders.model';
import {Order} from '../data/order';

@Component({
  selector: 'app-admin-unchecked-order',
  templateUrl: './admin-unchecked-order.component.html',
  styleUrls: ['./admin-unchecked-order.component.css']
})
export class AdminUncheckedOrderComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get orders(): Order[] {
    return this.ordersModel.orders;
  }

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected ordersModel: OrdersModel) {
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
    this.ordersModel.markAsSeen(order, true);
  }

  public download(order: Order): void {
    this.ordersModel.download(order);
  }

  public delete(order: Order): void {
    this.ordersModel.delete(order, true);
  }

}
