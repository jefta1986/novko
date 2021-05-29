import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() public product: Product;
  @Output() public addToCart: EventEmitter<object> = new EventEmitter<object>();
  public addToCartCount = 1;

  constructor() {
  }

  ngOnInit(): void {
  }

  public addedToCart(product): void {
    if (this.addToCartCount > 0) {
      this.addToCart.emit({product: product, count: this.addToCartCount});
    }
  }

  public incrementChange(number: number): void {
    this.addToCartCount = number;
  }

}
