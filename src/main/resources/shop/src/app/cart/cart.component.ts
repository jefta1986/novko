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

  constructor(private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              private _authService: AuthService,
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
    let errors = 0;
    this.products.forEach(product => {
      if (product.quantity < product.orderQuantity) {
        errors ++;
        this._snackBar.open(this.languageReplace(this.language.errorQuantity, ['quantity', 'name'], [product.quantity, product.name]), 'Error', {
          duration: 3000,
          panelClass: ['my-snack-bar-error']
        });
      }
    });
    if (errors === 0)
    {
      this.productModel.order(this.products);
    }
  }

  public removeFromCart(product: Product): void {
    this.productModel.removeFromCart(product);
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
