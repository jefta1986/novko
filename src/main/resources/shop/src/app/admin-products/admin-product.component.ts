import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {EditProductDialogComponent} from '../dialogs/edit-product-dialog/edit-product-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent extends CommonAbstractComponent implements OnInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addProduct, '/admin-orders-admin-add-product'),
  ];

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
