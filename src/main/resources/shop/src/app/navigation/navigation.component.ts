import {Component, OnInit, HostListener} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Utils} from '../app.utils';
import {Router} from '@angular/router';
import {MatSnackBar, MatDialog} from '@angular/material';
import {ProductService} from '../services/product.service';
import {SideBarComponent} from '../dialogs/side-bar/side-bar.component';
import {CategoryService} from '../services/category.service';
import {ProductModel} from '../models/productModel';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  public get totalAmount(): number {
    let total = 0;
    for (let i = 0; i < this.productModel.productsInCart.length; i++) {
      total += this.productModel.productsInCart[i].amountDin;
    }
    return total;
  }

  adminLoggedIn = false;
  userLoggedIn = false;
  categories;

  constructor(private _productService: ProductService,
              private _authService: AuthService,
              private _router: Router,
              private _snackBar: MatSnackBar,
              private _dialog: MatDialog,
              private _categoryService: CategoryService,
              public productModel: ProductModel) {
  }

  ngOnInit() {
    this.adminLoggedIn = AuthService.isAuthenticatedAdmin();
    this.userLoggedIn = AuthService.isAuthenticatedUser();
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

  logout() {
    this._authService.logout();
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
