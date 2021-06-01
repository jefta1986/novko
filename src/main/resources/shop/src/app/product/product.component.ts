import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product, ProductCount} from '../models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() public product: Product | null = null;
  @Output() public addToCart: EventEmitter<ProductCount> = new EventEmitter<ProductCount>();
  public addToCartCount = 1;

  constructor() {
  }

  ngOnInit(): void {
  }

  public addedToCart(product: Product | null): void {
    if (this.addToCartCount > 0) {
      const productCount = new ProductCount(product, this.addToCartCount);
      this.addToCart.emit(productCount);
    }
  }

  public incrementChange(number: number): void {
    this.addToCartCount = number;
  }

}
