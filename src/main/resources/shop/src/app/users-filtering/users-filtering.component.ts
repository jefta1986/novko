import {AfterViewInit, ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import {Pagination} from '../data/pagination';
import {DropdownValue} from '../data/dropdown-value';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CategoryService} from '../services/category.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSelectChange} from '@angular/material/select';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {UsersModel} from '../data/models/users.model';
import {UsersSort} from '../data/users.sort';

@Component({
  selector: 'app-users-filtering',
  templateUrl: './users-filtering.component.html',
  styleUrls: ['./users-filtering.component.css']
})
export class UsersFilteringComponent extends CommonAbstractComponent implements OnInit, AfterViewInit {

  // @ts-ignore
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  // @ts-ignore
  @ViewChild('inputEmail') inputEmail;
  // @ts-ignore
  @ViewChild('inputMb') inputMb;
  // @ts-ignore
  @ViewChild('inputPib') inputPib;

  public get pagination(): Pagination | null {
    return this._usersModel.pagination;
  }

  public get displaySearch(): boolean {
    return (this._authService.isAuthenticatedAdmin || false) && this._router.url.includes('admin-users');
  }

  public type: DropdownValue[] = [
    {value: 'ID', viewValue: this.language?.paginator.dropdown.price},
  ];
  public sort: DropdownValue[] = [
    {value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending},
    {value: 'DESC', viewValue: this.language?.paginator.dropdown.descending},
  ];

  public selectedType: string = this.type[0].value;
  public searchInputEmail: string = '';
  public searchInputMb: string = '';
  public searchInputPib: string = '';
  public selectedSort: string = this.sort[0].value;

  public pageIndex: number = 0;
  public pageSize: number = 12;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _categoryService: CategoryService,
              private _usersModel: UsersModel,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              private _authService: AuthService) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    this.commonLanguageModel.onLanguageChange.add(this.onLanguageChangeHandler, this);
    const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
    this.loadUsers(usersSort);
    this.translatePagination();
  }

  public ngOnDestroy(): void {
    this.commonLanguageModel.onLanguageChange.remove(this.onLanguageChangeHandler, this);
  }

  ngAfterViewInit() {
    super.ngAfterViewInit();
    this.inputEmail.valueChanges
      .pipe(debounceTime(500))
      .pipe(distinctUntilChanged())
      .subscribe(() => {
        const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
        this.loadUsers(usersSort);
      });
    this.inputMb.valueChanges
      .pipe(debounceTime(500))
      .pipe(distinctUntilChanged())
      .subscribe(() => {
        const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
        this.loadUsers(usersSort);
      });
    this.inputPib.valueChanges
      .pipe(debounceTime(500))
      .pipe(distinctUntilChanged())
      .subscribe(() => {
        const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
        this.loadUsers(usersSort);
      });
  }

  protected onLanguageChangeHandler(): void {
    this.translatePagination();
  }

  private loadUsers(usersSort: UsersSort): void {
    this._usersModel.loadUsersPaginated(usersSort, this.searchInputEmail, this.searchInputMb, this.searchInputPib);
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
    this.type.push({value: 'ID', viewValue: this.language?.paginator.dropdown.price});
    this.sort = [];
    this.sort.push({value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending});
    this.sort.push({value: 'DESC', viewValue: this.language?.paginator.dropdown.descending});
    this.selectedType = this.type[0].value;
    this.selectedSort = this.sort[0].value;
    this.paginator.ngOnInit();
  }

  public selectedSortChange($event: MatSelectChange): void {
    const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
    this.loadUsers(usersSort);
  }

  public pageChange($event: PageEvent): void {
    this.pageIndex = $event.pageIndex;
    this.pageSize = $event.pageSize;
    const usersSort = new UsersSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort);
    this.loadUsers(usersSort);
  }

}
