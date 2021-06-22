import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {ProductModel} from '../data/models/product.model';
import {Product, ProductCount} from '../data/product';
import {MatSnackBar} from '@angular/material/snack-bar';
import {NoItem} from '../common/common-language.interface';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent extends CommonAbstractComponent implements OnInit, AfterViewInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  public noItemType: NoItem = NoItem.productsHome;

  constructor(private _categoryService: CategoryService,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {}

  public addToCart(productCount: ProductCount): void {
    const {product, count} = productCount;
    if (product) {
      this._productModel.addToCart(product, count);
      this._snackBar.open(this.languageReplace(this.language.addedToCartName, ['name'], [product.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
  }

}
