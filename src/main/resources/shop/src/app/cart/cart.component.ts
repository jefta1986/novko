import {Component, OnInit} from '@angular/core';
import {Utils} from '../app.utils';
import {ProductService} from '../services/product.service';
import {ImageDialogComponent} from '../dialogs/image-dialog/image-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products = [];

  constructor(private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    Utils.getProductsFromCart().forEach(cartProduct => {
      this._productService.getProductByCode(cartProduct.code).subscribe(
        res => {
          res['numberInCart'] = 1;
          this.products.push(res);
        }
      );
    });
  }

  order() {
    console.log(this.products);
    this.products.forEach(element => {
      if (element.quantity < element.numberInCart) {
        this._snackBar.open('Only ' + element.quantity + ' pieces left for ' + element.name + '!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }
    });
  }

  removeFromCart(productName) {
    const cartProducts = Utils.getProductsFromCart();
    let i = 0;
    cartProducts.forEach(element => {
      if (element === productName) {
        cartProducts.splice(i, 1);
      }
      i++;
    });
    localStorage.setItem(Utils.cartArray, JSON.stringify(cartProducts));
    i = 0;
    this.products.forEach(element => {
      if (element.name === productName) {
        this.products.splice(i, 1);
      }
      i++;
    });

  }

  openImages(image) {
    let width;
    let height;
    const img = new Image();
    img.src = 'data:image/png;base64,' + image;
    const thisReference = this;
    img.addEventListener('load', function () {
      width = img.width + '';
      height = img.height + '';
      const dialogRef = thisReference._dialog.open(ImageDialogComponent, {
        width: width,
        height: height,
        data: image,
        panelClass: 'stretchedDialog'
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    });
  }

}
