import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() public product: Product;
  @Output() public addToCart: EventEmitter<Product> = new EventEmitter<Product>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public addedToCart(product): void {
    this.addToCart.emit(product);
  }

}
