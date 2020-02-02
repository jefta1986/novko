import { Component, OnInit } from '@angular/core';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  categories :Category[];
  products: Product[];

  constructor(private _categoryService:CategoryService,private _productServices:ProductService) { }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(
      res=>{
        this.categories = res;
      }
    );
    this._productServices.getAllProductsWithoutImages().subscribe(
      res=>{
        this.products = res;
      }
    );
  }

}
