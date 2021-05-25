import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { MatSnackBar, MatDialog } from '@angular/material';
import { EditProductDialogComponent } from '../dialogs/edit-product-dialog/edit-product-dialog.component';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  allProducts;

  constructor(private _productService: ProductService, private _dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this._productService.getAllProductsWithImages().subscribe(
      res => {
        this.allProducts = res;
      }
    );
  }

  delete(productName){
    this._productService.deleteProduct(productName).subscribe(res=>{}, err => {
      this._snackBar.open("Something went wrong,try again!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open("Product deleted!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this.ngOnInit();
    });
  }

  edit(productName: string) {
    var productToBeEdited = {};
    this.allProducts.forEach(element => {
      if (element.name == productName) {
        productToBeEdited = element;
      }
    });

    const dialogRef = this._dialog.open(EditProductDialogComponent, {
      width: '600px',
      height: '300px',
      data: productToBeEdited
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });

  }

}
