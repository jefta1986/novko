import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get products(): Product[] {
    return this._productModel.products;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addProduct, '/admin-orders-admin-add-product'),
  ];

  constructor(private _productModel: ProductModel,
              private _dialog: MatDialog,
              private _router: Router,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    super.ngOnInit();
    this._productModel.loadProducts();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  delete(product: Product) {
    this._productModel.deleteProductByCode(product);
  }

  edit(product: Product) {
    this._productModel.setEditProduct(product);
    this._router.navigate(['/admin-products/', product.code]);
  }

}
