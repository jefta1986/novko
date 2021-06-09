import {ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product, ProductCount} from '../data/product';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';;
import {AuthService} from '../services/auth.service';
import {LanguageTypes} from '../common/abstract-language.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent extends CommonAbstractComponent implements OnInit {

  @Input() public product: Product | null = null;
  @Output() public addToCart: EventEmitter<ProductCount> = new EventEmitter<ProductCount>();
  public addToCartCount = 1;

  public get amount(): number {
    if (this._authService.user && this.product) {
      return this._authService.user.language === LanguageTypes.SR ? this.product.amountDin : this.product.amountEuro;
    }
    return this.product ? this.product.amountDin : 0;
  }

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected _authService: AuthService) {
    super(cdr, commonLanguageModel);
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
