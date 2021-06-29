import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Order} from '../data/order';
import {Pagination, PaginationRequest} from '../data/pagination';
import {NoItem} from '../common/common-language.interface';
import {CommonLanguageModel} from '../common/common-language.model';
import {OrdersModel} from '../data/models/orders.model';
import {PageEvent} from '@angular/material/paginator';
import {CommonAbstractComponent} from '../common/common-abstract-component';

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent extends CommonAbstractComponent implements OnInit {

  public get orders(): Order[] {
    return this.ordersModel.orders;
  }

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public noItemType: NoItem = NoItem.orders;

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected ordersModel: OrdersModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.ordersModel.loadOrdersUsername();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

}
