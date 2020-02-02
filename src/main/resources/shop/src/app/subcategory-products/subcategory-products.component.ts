import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-subcategory-products',
  templateUrl: './subcategory-products.component.html',
  styleUrls: ['./subcategory-products.component.css']
})
export class SubcategoryProductsComponent implements OnInit {

  categories :Category[];
  products: Product[];
  selectedSubcategory;

  constructor(private _activatedRoute:ActivatedRoute,private _router:Router,private _categoryService:CategoryService,private _productServices:ProductService) { }

  ngOnInit() {
    this.selectedSubcategory = this._activatedRoute.snapshot.paramMap.get('subcategory');
    this._categoryService.getAllCategories().subscribe(
      res=>{
        this.categories = res;
      }
    );
    this._productServices.getProductsFromSubcategories(this.selectedSubcategory).subscribe(
      res=>{
        this.products = res.products;
      }
    );
  }

}
