import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {OrdersModel} from '../data/models/orders.model';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['./admin-order.component.css']
})
export class AdminOrderComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

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

}
