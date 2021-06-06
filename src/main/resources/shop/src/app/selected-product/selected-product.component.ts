import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-selected-product',
  templateUrl: './selected-product.component.html',
  styleUrls: ['./selected-product.component.css']
})
export class SelectedProductComponent implements OnInit {

  public get product(): Product {
    return this._productModel.product;
  }

  constructor(private _activatedRoute: ActivatedRoute,
              private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected _productModel: ProductModel,
              private _router: Router) {
  }

  ngOnInit() {
    const code = this._activatedRoute.snapshot.paramMap.get('code');
    if (code !== null) {
      this._productModel.loadProductByCode(code);
    } else {
      this._router.navigate(['/home']);
    }
  }

  addToCart() {
    if (this.product) {
      this._productModel.addToCart(this.product, 1);
      this._snackBar.open('Product added to the cart!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
  }
  //
  // openImages(image) {
  //   let width;
  //   let height;
  //   let img = new Image();
  //   img.src = 'data:image/png;base64,' + image;
  //   let thisReference = this;
  //   img.addEventListener('load', function () {
  //     width = img.width + '';
  //     height = img.height + '';
  //     const dialogRef = thisReference._dialog.open(ImageDialogComponent, {
  //       width: width,
  //       height: height,
  //       data: image,
  //       panelClass: 'stretchedDialog'
  //     });
  //
  //     dialogRef.afterClosed().subscribe(result => {
  //       thisReference.ngOnInit();
  //     });
  //   });
  // }

}
