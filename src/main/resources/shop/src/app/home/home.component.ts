import { Component, OnInit, ViewChild } from '@angular/core';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';
import { Utils } from '../app.utils';
import { NavigationComponent } from '../navigation/navigation.component';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  categories :Category[];
  products: Product[];
  @ViewChild('navigation') navigationComponent:NavigationComponent;

  constructor(private _categoryService:CategoryService,private _productServices:ProductService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(
      res=>{
        this.categories = res;
      }
    );
    this._productServices.getAllProductsWithoutImages().subscribe(
      res=>{
        this.products = res;
        console.log(res)
        if(Utils.getProductsFromCart() != null){
          this.products.forEach(product => {
            Utils.getProductsFromCart().forEach(cartProduct => {
                if(product.name == cartProduct){
                  product['addedToCart'] = true;
                }
            });
          });
        }
      }
    );
  }

  addToCart(productName){
    let productsToCart = Utils.getProductsFromCart();
    let alreadyAdded = false;
    if(productsToCart != null){
      productsToCart.forEach(element => {
        if(element == productName){
          alreadyAdded = true;
          this._snackBar.open("Product already added to the cart!", 'Error', {
            duration: 4000,
            panelClass: ['my-snack-bar-error']
          });
        }
      });
    }
    if(!alreadyAdded){
      Utils.addToCart(productName);
      this._snackBar.open("Product added to the cart!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
    this.navigationComponent.ngOnInit();
  }

}
