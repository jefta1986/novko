import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {Category} from '../models/category';
import {ProductService} from '../services/product.service';
import {Router, ActivatedRoute} from '@angular/router';
import {Utils} from '../app.utils';

@Component({
  selector: 'app-subcategory-products',
  templateUrl: './subcategory-products.component.html',
  styleUrls: ['./subcategory-products.component.css']
})
export class SubcategoryProductsComponent implements OnInit {

  categories: Category[];
  products;
  selectedSubcategory;

  constructor(private _activatedRoute: ActivatedRoute,
              private _router: Router,
              private _categoryService: CategoryService,
              private _productServices: ProductService) {}

  ngOnInit() {
    this.selectedSubcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    this._productServices.getProductsFromSubcategories(this.selectedSubcategory).subscribe(
      res => {
        this.products = res.products;
        if (Utils.getProductsFromCart() != null) {
          this.products.forEach(product => {
            Utils.getProductsFromCart().forEach(cartProduct => {
              if (product.name === cartProduct) {
                product['addedToCart'] = true;
              }
            });
          });
        }
      }
    );
  }

}
