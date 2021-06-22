import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Category} from '../data/category';
import {Router, ActivatedRoute} from '@angular/router';
import {Product, ProductCount} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CategoriesModel} from '../data/models/categories.model';
import {Subcategory} from '../data/subcategory';
import {AuthService} from '../services/auth.service';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {NoItem} from '../common/common-language.interface';
import {ProductsSort} from '../data/products.sort';

@Component({
  selector: 'app-subcategory-products',
  templateUrl: './subcategory-products.component.html',
  styleUrls: ['./subcategory-products.component.css']
})
export class SubcategoryProductsComponent extends CommonAbstractComponent implements OnInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  public get subcategories(): Subcategory[] {
    return this._categoriesModel.subCategories;
  }

  public get categories(): Category[] {
    return this._categoriesModel.categories;
  }

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public get categoryName(): string {
    if (this._categoriesModel.subCategory) {
      return this.isSerbian ? this._categoriesModel.subCategory.nameSr : this._categoriesModel.subCategory.name;
    }
    return '';
  }

  public noItemType: NoItem = NoItem.products;

  public selectedSubcategory: string | null = null;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _categoriesModel: CategoriesModel,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar,
              private _authService: AuthService,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    _activatedRoute.params.subscribe(val => {
      this.changeSubcategory();
    });
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  private changeSubcategory(): void {
    this.selectedSubcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    if (this.selectedSubcategory) {
      this._categoriesModel.setSubcategory(this.selectedSubcategory);
    }
  }

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
