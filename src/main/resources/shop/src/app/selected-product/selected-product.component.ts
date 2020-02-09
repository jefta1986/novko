import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service'
import { Product } from '../models/product';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ImageDialogComponent } from '../dialogs/image-dialog/image-dialog.component';
import { Utils } from '../app.utils';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-selected-product',
  templateUrl: './selected-product.component.html',
  styleUrls: ['./selected-product.component.css']
})
export class SelectedProductComponent implements OnInit {

  product: Product;
  images = [];
  @ViewChild('navigation') navigationComponent: NavigationComponent;

  addedToCart = false;

  constructor(private _activatedRoute: ActivatedRoute
    , private _productService: ProductService
    , private _dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this._productService.getProductByName(this._activatedRoute.snapshot.paramMap.get('productName'))
      .subscribe(
        res => {
          this.product = res;
          this.product.images.forEach(element => {
            this.images.push('data:image/png;base64,' + element.data);
          });
          if (Utils.getProductsFromCart() != null) {
            Utils.getProductsFromCart().forEach(element => {
              if (element == this.product.name) {
                this.addedToCart = true;
              }
            });
          }
        }
      );
  }

  addToCart() {
    let productsToCart = Utils.getProductsFromCart();
    let alreadyAdded = false;
    if (productsToCart != null) {
      productsToCart.forEach(element => {
        if (element == this.product.name) {
          alreadyAdded = true;
          this._snackBar.open("Product already added to the cart!", 'Error', {
            duration: 4000,
            panelClass: ['my-snack-bar-error']
          });
        }
      });
    }
    if (!alreadyAdded) {
      Utils.addToCart(this.product.name);
      this._snackBar.open("Product added to the cart!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
    this.navigationComponent.ngOnInit();
  }

  //not used currently
  openImages(image) {
    let width;
    let height;
    let img = new Image();
    img.src = 'data:image/png;base64,' + image;
    let thisReference = this;
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
        thisReference.ngOnInit();
      });
    });
  }

}
