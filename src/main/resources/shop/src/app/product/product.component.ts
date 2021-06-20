import {ChangeDetectorRef, Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Product, ProductCount} from '../data/product';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';

;
import {AuthService} from '../services/auth.service';
import {LanguageTypes} from '../common/abstract-language.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  @Input() public product: Product | null = null;
  @Output() public addToCart: EventEmitter<ProductCount> = new EventEmitter<ProductCount>();
  public addToCartCount = 1;

  public slideConfig: any = {
    infinite: true,
    autoplay: true,
    speed: 500,
    fade: true,
    cssEase: 'linear',
    dots: false,
    prevArrow: false,
    nextArrow: false
  };

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public get amount(): number {
    if (this.product) {
      return this.isSerbian ? this.product.amountDin : this.product.amountEuro;
    }

    return 0;
  }

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              protected _authService: AuthService) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
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
