import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ProductService} from '../services/product.service'
import { Product } from '../models/product';

@Component({
  selector: 'app-selected-product',
  templateUrl: './selected-product.component.html',
  styleUrls: ['./selected-product.component.css']
})
export class SelectedProductComponent implements OnInit {

  product:Product;

  constructor(private _activatedRoute:ActivatedRoute,private _productService:ProductService) { }

  ngOnInit() {
    this._productService.getProductByName(this._activatedRoute.snapshot.paramMap.get('productName'))
          .subscribe(
            res=>{
              this.product = res;
              console.log(this.product)
            }
          );
  }

}
