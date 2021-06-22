import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Utils} from '../app.utils';
import {ProductService} from '../services/product.service';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {LanguageTypes} from '../common/abstract-language.model';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get products(): Product[] {
    return this.productModel.productsInCart;
  }

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public get total(): number {
    let total = 0;
    this.products.forEach(product => total += product.orderQuantity * (this.isSerbian ? product.amountDin : product.amountEuro));
    return total;
  }

  public get totalRebate(): number {
    let total = this.total;
    const rebate = this._authService.user?.rabat || 0;
    const rebateTotal = this.total * rebate;
    return total - rebateTotal;
  }

  public get totalTax(): number {
    const tax = this.totalRebate * 0.2;
    return tax + this.totalRebate;
  }

  constructor(private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              private _authService: AuthService,
              private _router: Router,
              public productModel: ProductModel,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public order(): void {
    this.productModel.order(this.products);
  }

  public removeFromCart(product: Product): void {
    this.productModel.removeFromCart(product);
    if (this.products.length === 0) {
      this._router.navigate(['/']);
    }
  }

  public incrementChange(number: number, product: Product): void {
    if (number > product.quantity) {
      this._snackBar.open(this.languageReplace(this.language.errorQuantity, ['quantity', 'name'], [product.quantity, product.name]), 'Error', {
        duration: 3000,
        panelClass: ['my-snack-bar-error']
      });
    }
    this.productModel.changeCartNumber(number, product);
  }

}
