import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {OrdersModel} from '../data/models/orders.model';
import {Order} from '../data/order';
import {AdditionalLinks} from '../data/additional-links';
import {Pagination, PaginationRequest} from '../data/pagination';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get orders(): Order[] {
    return this.ordersModel.orders;
  }

  public get pagination(): Pagination | null {
    return this.ordersModel.pagination;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.orders, '/admin-unchecked-orders'),
  ];

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected ordersModel: OrdersModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.ordersModel.loadOrdersPaginated();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public markAsSeen(order: Order): void {
    this.ordersModel.markAsSeen(order, false);
  }

  public download(order: Order): void {
    this.ordersModel.download(order);
  }

  public delete(order: Order): void {
    this.ordersModel.delete(order, false);
  }

  public pageChange($event: PageEvent): void {
    const params = new PaginationRequest($event.pageIndex, $event.pageSize);
    this.ordersModel.loadOrdersPaginated(params);
  }

}
