import {Component, OnInit, ChangeDetectorRef, Output, EventEmitter, OnDestroy} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {ProductModel} from '../data/models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {Product} from '../data/product';
import {Category} from '../data/category';
import {CategoriesModel} from '../data/models/categories.model';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get email(): string | undefined {
    return this._authService.user?.username;
  }

  public get userLoggedIn(): boolean {
    return this._authService.user !== null;
  }

  public get adminLoggedIn(): boolean | null {
    return this._authService.isAuthenticatedAdmin;
  }

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public get totalAmount(): number {
    let total = 0;
    for (let i = 0; i < this.productModel.productsInCart.length; i++) {
      total += this.productModel.productsInCart[i].orderQuantity * (this.isSerbian ? this.productModel.productsInCart[i].amountDin : this.productModel.productsInCart[i].amountDin);
    }
    return total;
  }

  public get categories(): Category[] {
    return this._categoriesModel.categories;
  }

  public showUserMenu: boolean = false;
  public showCartMenu: boolean = false;

  @Output() logoLoaded: EventEmitter<void> = new EventEmitter<void>();
  @Output() navigationToggled: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private _productService: ProductService,
              private _authService: AuthService,
              private _router: Router,
              private _snackBar: MatSnackBar,
              private _dialog: MatDialog,
              private _categoriesModel: CategoriesModel,
              public productModel: ProductModel,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    if (this.categories.length === 0) {
      this._categoriesModel.loadCategoriesSubcategories();
    }
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public goToCart() {
    if (this.productModel.productsInCart.length) {
      this._router.navigate(['cart']);
    }
  }

  public deleteFromCart(product: Product): void {
    this.productModel.removeFromCart(product);
    this._snackBar.open(this.languageReplace(this.language.removedFromCartName, ['name'], [product.name]), 'Success', {
      duration: 4000,
      panelClass: ['my-snack-bar']
    });
  }

  public logout(): void {
    this._authService.logout(false);
  }

  public openSideBar(): void {
    this.navigationToggled.emit(true);
  }

  public showCart($event: Event): void {
    this.showCartMenu = true;
  }

  public hideCart($event: Event): void {
    this.showCartMenu = false;
  }

  public showUser($event: Event): void {
    this.showUserMenu = true;
  }

  public hideUser($event: Event): void {
    const event = $event as any;

    if (event.relatedTarget && event.relatedTarget.tagName !== 'INPUT' ||
      event.toElement && event.toElement.tagName !== 'INPUT') {
      this.showUserMenu = false;
    }
  }

}
