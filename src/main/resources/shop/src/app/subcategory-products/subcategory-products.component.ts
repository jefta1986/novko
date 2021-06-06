import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {Category} from '../data/category';
import {ProductService} from '../services/product.service';
import {Router, ActivatedRoute} from '@angular/router';
import {Product, ProductCount} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-subcategory-products',
  templateUrl: './subcategory-products.component.html',
  styleUrls: ['./subcategory-products.component.css']
})
export class SubcategoryProductsComponent implements OnInit {

  public categories: Category[] = [];
  public products: Product[] = [];
  public selectedSubcategory: string | null = null;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _categoryService: CategoryService,
              private _productServices: ProductService,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar) {}

  public ngOnInit(): void {
    this.selectedSubcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    // this._productServices.getProductsFromSubcategories(this.selectedSubcategory).subscribe(
    //   res => {
    //     this.products = res.products;
    //     if (Utils.getProductsFromCart() != null) {
    //       this.products.forEach(product => {
    //         Utils.getProductsFromCart().forEach(cartProduct => {
    //           if (product.name === cartProduct) {
    //             product['addedToCart'] = true;
    //           }
    //         });
    //       });
    //     }
    //   }
    // );
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
