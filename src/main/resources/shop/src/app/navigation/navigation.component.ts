import { Component, OnInit, HostListener } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Utils } from '../app.utils';
import { Router } from '@angular/router';
import { MatSnackBar, MatDialog } from '@angular/material';
import { ProductService } from '../services/product.service';
import { SideBarComponent } from '../dialogs/side-bar/side-bar.component';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  adminLoggedIn = false;
  userLoggedIn = false;
  numberOfItemsInCart = 0;
  productsInCart = [];
  showCartHover = false;
  categories;

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.showCartContent();
  }

  constructor(private _productService: ProductService
    , private _authService: AuthService, private _router: Router
    , private _snackBar: MatSnackBar,private _dialog: MatDialog
    , private _categoryService:CategoryService) { }

  ngOnInit() {
    this.adminLoggedIn = AuthService.isAuthenticatedAdmin();
    this.userLoggedIn = AuthService.isAuthenticatedUser();
    this.getNumberOfItemsInCart();
    this.showCartContent();
    this._categoryService.getAllCategories().subscribe(
      res=>{
        this.categories = res;
      }
    );
  }

  goToCart() {
    if (this.numberOfItemsInCart > 0) {
      this._router.navigate(['cart']);
    } else {
      this._snackBar.open("Cart is empty, please add product to the cart first!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }
  }

  getNumberOfItemsInCart() {
    this.numberOfItemsInCart = 0;
    if (localStorage.getItem(Utils.cartArray) != null) {
      let array = JSON.parse(localStorage.getItem(Utils.cartArray));
      array.forEach(element => {
        this._productService.getProductByName(element).subscribe(
          res => {
            this.productsInCart.push(res);
          }
        );
        this.numberOfItemsInCart++;
      });
    }

  }

  logout() {
    this._authService.logout();
  }

  showCartContent(){
    let innerWidth = window.innerWidth;
    if(innerWidth > 770){
      this.showCartHover = true;
    }else{
      this.showCartHover = false;
    }
    return this.showCartHover;
  }

  openSideBar(){
    const dialogRef = this._dialog.open(SideBarComponent, {
      //this is for full screen with little padding
      // maxWidth: '97vw',
      // maxHeight: '97vh',
      // height: '97%',
      // width: '97%',
      width: '400px',
      height: '100%',
      position: { left: '0'},
      data: this.categories
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });

  }

}
