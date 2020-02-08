import { Component, OnInit } from '@angular/core';
import { Utils } from '../app.utils';
import { ProductService } from '../services/product.service';
import { MatDialog } from '@angular/material';
import { ImageDialogComponent } from '../dialogs/image-dialog/image-dialog.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products = [];

  constructor(private _productService: ProductService, private _dialog: MatDialog) { }

  ngOnInit() {
    Utils.getProductsFromCart().forEach(cartProduct => {
      this._productService.getProductByName(cartProduct).subscribe(
        res => {
          res['numberInCart'] = 1;
          this.products.push(res);
        }
      );
    });
    console.log(this.products)
  }

  openImages(image) {
    console.log(image)
    let width;
    let height;
    let img = new Image();
    img.src = 'data:image/png;base64,' + image;
    let thisReference = this;
    img.addEventListener('load', function () {
      width = img.width + '';
      height = img.height + '';
      console.log(width + height);
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
