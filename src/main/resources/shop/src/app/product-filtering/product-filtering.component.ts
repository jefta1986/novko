import {AfterViewInit, ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import {Pagination} from '../data/pagination';
import {DropdownValue} from '../data/dropdown-value';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CategoryService} from '../services/category.service';
import {ProductModel} from '../data/models/product.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonLanguageModel} from '../common/common-language.model';
import {ProductsSort} from '../data/products.sort';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSelectChange} from '@angular/material/select';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-product-filtering',
  templateUrl: './product-filtering.component.html',
  styleUrls: ['./product-filtering.component.css']
})
export class ProductFilteringComponent extends CommonAbstractComponent implements OnInit, AfterViewInit {

  // @ts-ignore
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  public get pagination(): Pagination | null {
    return this._productModel.pagination;
  }

  public type: DropdownValue[] = [
    {value: 'AMOUNT_DIN', viewValue: this.language?.paginator.dropdown.price},
    {value: 'NEWEST', viewValue: this.language?.paginator.dropdown.time},
  ];
  public sort: DropdownValue[] = [
    {value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending},
    {value: 'DESC', viewValue: this.language?.paginator.dropdown.descending},
  ];

  public selectedType = this.type[0].value;
  public selectedSort = this.sort[0].value;

  public pageIndex: number = 0;
  public pageSize: number = 12;

  constructor(private _activatedRoute: ActivatedRoute,
              private _categoryService: CategoryService,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    _activatedRoute.params.subscribe(val => {
      const productsSort = new ProductsSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, true);
      this.loadProducts(productsSort);
    });
  }

  ngOnInit(): void {
    this.commonLanguageModel.onLanguageChange.add(this.onLanguageChangeHandler, this);
    const productsSort = new ProductsSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, true);
    this.loadProducts(productsSort);
    this.translatePagination();
  }

  public ngOnDestroy(): void {
    this.commonLanguageModel.onLanguageChange.remove(this.onLanguageChangeHandler, this);
  }

  protected onLanguageChangeHandler(): void {
    this.translatePagination();
  }

  private loadProducts(productsSort: ProductsSort): void {
    const subcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    if (subcategory) {
      this._productModel.loadProductsSubcategoryPaginated(productsSort, subcategory);
    } else {
      this._productModel.loadProductsPaginated(productsSort);
    }
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
    this.type.push({value: 'AMOUNT_DIN', viewValue: this.language?.paginator.dropdown.price});
    this.type.push({value: 'NEWEST', viewValue: this.language?.paginator.dropdown.time});
    this.sort = [];
    this.sort.push({value: 'ASC', viewValue: this.language?.paginator.dropdown.ascending});
    this.sort.push({value: 'DESC', viewValue: this.language?.paginator.dropdown.descending});
    this.selectedType = this.type[0].value;
    this.selectedSort = this.sort[0].value;
    this.paginator.ngOnInit();
  }

  public selectedTypeChange($event: MatSelectChange): void {
    const productsSort = new ProductsSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, true);
    this.loadProducts(productsSort);
  }

  public selectedSortChange($event: MatSelectChange): void {
    const productsSort = new ProductsSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, true);
    this.loadProducts(productsSort);
  }

  public pageChange($event: PageEvent): void {
    this.pageIndex = $event.pageIndex;
    this.pageSize = $event.pageSize;
    const productsSort = new ProductsSort(this.pageIndex, this.pageSize, this.selectedType, this.selectedSort, true);
    this.loadProducts(productsSort);
  }

}
