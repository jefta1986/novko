import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { MatSnackBar } from '@angular/material';
import { ProductModel } from '../models/product.model';
import { Product } from '../models/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public get products(): Product[] {
    return this._productModel.products;
  }

  constructor(private _categoryService: CategoryService,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {}

  public addToCart(product): void {
    this._productModel.addToCart(product);
    this._snackBar.open(`Product ${product.name} added to the cart!`, 'Success', {
      duration: 4000,
      panelClass: ['my-snack-bar']
    });
  }

}
