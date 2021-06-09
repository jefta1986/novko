import {Component, OnInit} from '@angular/core';
import {Category} from '../data/category';
import {Router, ActivatedRoute} from '@angular/router';
import {Product, ProductCount} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CategoriesModel} from '../data/models/categories.model';
import {Subcategory} from '../data/subcategory';

@Component({
  selector: 'app-subcategory-products',
  templateUrl: './subcategory-products.component.html',
  styleUrls: ['./subcategory-products.component.css']
})
export class SubcategoryProductsComponent implements OnInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  public get subcategories(): Subcategory[] {
    return this._categoriesModel.subCategories;
  }

  public categories: Category[] = [];
  public selectedSubcategory: string | null = null;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _categoriesModel: CategoriesModel,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar) {
    _activatedRoute.params.subscribe(val => {
      this.loadProductsForCategory();
    });
  }

  public ngOnInit(): void {
    this.loadProductsForCategory();
  }

  public loadProductsForCategory(): void {
    this.selectedSubcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    if (this.selectedSubcategory) {
      this._productModel.loadProductsBySubcategory(this.selectedSubcategory);
    } else {
      this._router.navigate(['home']);
    }
  }

  public addToCart(productCount: ProductCount): void {
    const {product, count} = productCount;
    if (product) {
      this._productModel.addToCart(product, count);
      this._snackBar.open(`Product ${product.name} added to the cart!`, 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
  }

}
