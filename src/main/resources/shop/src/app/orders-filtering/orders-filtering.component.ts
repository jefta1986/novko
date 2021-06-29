import {AfterViewInit, ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import {Pagination} from '../data/pagination';
import {DropdownValue, StatusDropdownValue} from '../data/dropdown-value';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CategoryService} from '../services/category.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSelectChange} from '@angular/material/select';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {OrdersModel} from '../data/models/orders.model';
import {OrdersSort} from '../data/orders.sort';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {Moment} from 'moment';
import * as moment from 'moment';

@Component({
  selector: 'app-orders-filtering',
  templateUrl: './orders-filtering.component.html',
  styleUrls: ['./orders-filtering.component.css']
})
export class OrdersFilteringComponent extends CommonAbstractComponent implements OnInit, AfterViewInit {

  // @ts-ignore
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  // @ts-ignore
  @ViewChild('input') input;

  public get pagination(): Pagination | null {
    return this._ordersModel.pagination;
  }

  public get displaySearch(): boolean {
    return (this._authService.isAuthenticatedAdmin || false) && this._router.url.includes('admin-orders');
  }

  public type: DropdownValue[] = [
    {value: 'NEWEST', viewValue: this.language?.paginator.dropdown.time},
  ];
  public status: StatusDropdownValue[] = [
    {value: true, viewValue: this.language?.wasSeen},
    {value: false, viewValue: this.language?.wasntSeen},
    {value: null, viewValue: '--'},
  ];
  public sort: DropdownValue[] = [
    {value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending},
    {value: 'DESC', viewValue: this.language?.paginator.dropdown.descending},
  ];

  public selectedType: string = this.type[0].value;
  public searchInput: string = '';
  public selectedSort: string = this.sort[0].value;
  public selectedStatus: boolean | null = this.status[0].value;

  public pageIndex: number = 0;
  public pageSize: number = 12;
  public startDate: Moment | null = null;
  public endDate: Moment | null = null;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _ordersModel: OrdersModel,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              private _authService: AuthService) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    this.commonLanguageModel.onLanguageChange.add(this.onLanguageChangeHandler, this);
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
    this.translatePagination();
  }

  public ngOnDestroy(): void {
    this.commonLanguageModel.onLanguageChange.remove(this.onLanguageChangeHandler, this);
  }

  ngAfterViewInit() {
    super.ngAfterViewInit();
    this.input.valueChanges
      .pipe(debounceTime(500))
      .pipe(distinctUntilChanged())
      .subscribe(() => {
        this.searchChanged();
      });
  }

  protected onLanguageChangeHandler(): void {
    this.translatePagination();
  }

  private loadOrders(ordersSort: OrdersSort): void {
    this._ordersModel.loadOrdersPaginated(ordersSort, this.searchInput);
  }

  private translatePagination(): void {
    this.paginator._intl.itemsPerPageLabel = this.language?.paginator.paginatorIntl.itemsPerPageLabel;
    this.paginator._intl.nextPageLabel = this.language?.paginator.paginatorIntl.nextPageLabel;
    this.paginator._intl.previousPageLabel = this.language?.paginator.paginatorIntl.previousPageLabel;
    this.paginator._intl.firstPageLabel = this.language?.paginator.paginatorIntl.firstPageLabel;
    this.paginator._intl.lastPageLabel = this.language?.paginator.paginatorIntl.lastPageLabel;
    const originalGetRangeLabel = this.paginator._intl.getRangeLabel;
    this.paginator._intl.getRangeLabel = (page: number, size: number, len: number) => {
      return originalGetRangeLabel(page, size, len)
        .replace('of', this.language?.paginator.of);
    };
    this.type = [];
    this.type.push({value: 'NEWEST', viewValue: this.language?.paginator.dropdown.time});
    this.sort = [];
    this.sort.push({value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending});
    this.sort.push({value: 'DESC', viewValue: this.language?.paginator.dropdown.descending});
    this.selectedType = this.type[0].value;
    this.selectedSort = this.sort[0].value;
    this.paginator.ngOnInit();
  }

  public searchChanged(): void {
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
  }

  public selectedStatusChange($event: MatSelectChange): void {
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
  }

  public selectedTypeChange($event: MatSelectChange): void {
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
  }

  public selectedSortChange($event: MatSelectChange): void {
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
  }

  public pageChange($event: PageEvent): void {
    this.pageIndex = $event.pageIndex;
    this.pageSize = $event.pageSize;
    const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus);
    this.loadOrders(ordersSort);
  }

  public startDateChanged($event: MatDatepickerInputEvent<any>) {
    const {value} = $event;
    this.startDate = moment(value).startOf('date');
    if (this.endDate) {
      const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus, this.startDate, this.endDate);
      this.loadOrders(ordersSort);
    }
  }

  public endDateChanged($event: MatDatepickerInputEvent<any>) {
    const {value} = $event;
    this.endDate = moment(value).endOf('date');
    if (this.startDate) {
      const ordersSort = new OrdersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, this.selectedStatus, this.startDate, this.endDate);
      this.loadOrders(ordersSort);
    }
  }

}
