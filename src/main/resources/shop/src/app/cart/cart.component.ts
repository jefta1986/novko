import {Component, OnInit} from '@angular/core';
import {Utils} from '../app.utils';
import {ProductService} from '../services/product.service';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../models/product';
import {ProductModel} from '../models/product.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public get products(): Product[] {
    return this.productModel.productsInCart;
  }

  constructor(private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              public productModel: ProductModel) {
  }

  ngOnInit() {

  }

  order() {
    this.products.forEach(element => {
      if (element.quantity < element.orderQuantity) {
        this._snackBar.open('Only ' + element.quantity + ' pieces left for ' + element.name + '!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }
    });
  }

  removeFromCart(product: Product) {
    this.productModel.removeFromCart(product);
  }

  // openImages(image) {
  // let width;
  // let height;
  // const img = new Image();
  // img.src = 'data:image/png;base64,' + image;
  // const thisReference = this;
  // img.addEventListener('load', function () {
  //   width = img.width + '';
  //   height = img.height + '';
  //   const dialogRef = thisReference._dialog.open(ImageDialogComponent, {
  //     width: width,
  //     height: height,
  //     data: image,
  //     panelClass: 'stretchedDialog'
  //   });
  //
  //   dialogRef.afterClosed().subscribe(result => {
  //   });
  // });
  // }

}
