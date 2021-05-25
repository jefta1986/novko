import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {Utils} from '../app.utils';
import {MatSnackBar} from '@angular/material';
import {ProductModel} from '../models/productModel';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private _categoryService: CategoryService,
              private productModel: ProductModel,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {}

  addToCart(product) {
    this.productModel.addToCart(product);
    this._snackBar.open('Product added to the cart!', 'Success', {
      duration: 4000,
      panelClass: ['my-snack-bar']
    });
  }

}
