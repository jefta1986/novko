import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {EditProductDialogComponent} from '../dialogs/edit-product-dialog/edit-product-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../models/product';
import {ProductModel} from '../models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent extends CommonAbstractComponent implements OnInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  constructor(private _productModel: ProductModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    this._productModel.loadProducts();
  }

  delete(product: Product) {
    this._productModel.deleteProduct(product);
  }

  edit(product: Product) {
    this._dialog.open(EditProductDialogComponent, {
      width: '600px',
      height: '300px',
      data: product
    });
  }

}
