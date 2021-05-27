import {Component, OnInit, HostListener, ChangeDetectorRef} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Utils} from '../app.utils';
import {Router} from '@angular/router';
import {MatSnackBar, MatDialog} from '@angular/material';
import {ProductService} from '../services/product.service';
import {SideBarComponent} from '../dialogs/side-bar/side-bar.component';
import {CategoryService} from '../services/category.service';
import {ProductModel} from '../models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent extends CommonAbstractComponent implements OnInit {

  public get email(): string {
    return this._authService.user.username;
  }

  public get userLoggedIn(): boolean {
    return this._authService.user !== null;
  }

  public get adminLoggedIn(): boolean {
    return this._authService.isAuthenticatedAdmin;
  }

  public get totalAmount(): number {
    let total = 0;
    for (let i = 0; i < this.productModel.productsInCart.length; i++) {
      total += this.productModel.productsInCart[i].amountDin;
    }
    return total;
  }

  categories;

  constructor(private _productService: ProductService,
              private _authService: AuthService,
              private _router: Router,
              private _snackBar: MatSnackBar,
              private _dialog: MatDialog,
              private _categoryService: CategoryService,
              public productModel: ProductModel,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(
      res => {
        this.categories = res;
      }
    );
  }

  goToCart() {
    if (this.productModel.productsInCart.length) {
      this._router.navigate(['cart']);
    } else {
      this._snackBar.open('Cart is empty, please add product to the cart first!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }
  }

  public deleteFromCart(product): void {
    this.productModel.removeFromCart(product);
    this._snackBar.open(`Product ${product.name} removed from cart!`, 'Success', {
      duration: 4000,
      panelClass: ['my-snack-bar']
    });
  }

  logout() {
    this._authService.logout(false);
  }

  openSideBar() {
    const dialogRef = this._dialog.open(SideBarComponent, {
      width: '400px',
      height: '100%',
      position: {left: '0'},
      data: this.categories
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });

  }

}
