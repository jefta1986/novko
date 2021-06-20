import {ChangeDetectorRef, Component, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {LanguageTypes} from '../common/abstract-language.model';
import {AuthService} from '../services/auth.service';
import {TimeInterval} from 'rxjs';

@Component({
  selector: 'app-selected-product',
  templateUrl: './selected-product.component.html',
  styleUrls: ['./selected-product.component.css'],
})
export class SelectedProductComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get product(): Product {
    return this._productModel.product;
  }

  public get amount(): number {
    if (this._authService.user && this.product) {
      return this._authService.user.language === LanguageTypes.SR ? this.product.amountDin : this.product.amountEuro;
    }
    return this.product ? this.product.amountDin : 0;
  }

  public get categoryName(): string {
    if (this._authService.user && this.product) {
      return this._authService.user.language === LanguageTypes.SR ? this.product.subcategory.nameSr : this.product.subcategory.name;
    }
    return this.product ? this.product.subcategory.nameSr : '';
  }

  public get productName(): string {
    if (this._authService.user && this.product) {
      return this._authService.user.language === LanguageTypes.SR ? this.product.nameSr : this.product.name;
    }
    return this.product ? this.product.nameSr : '';
  }

  public addToCartCount = 1;
  public selectedImage: string = '';
  public srcViewImg: string = '';
  public imgTransition: boolean = false;
  private _slideInterval: number | undefined = undefined;

  constructor(private _activatedRoute: ActivatedRoute,
              private _productService: ProductService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected _productModel: ProductModel,
              private _router: Router,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              private _authService: AuthService) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    const code = this._activatedRoute.snapshot.paramMap.get('code');
    if (code !== null) {
      this._productModel.loadProductByCode(code).subscribe((product) => {
        if (product.images.length > 0) {
          this.selectedImage = product.images[0];
          this._slideInterval = setInterval(() => this.changeIntervalImage(), 3500);
        }
      });
    } else {
      this._router.navigate(['/home']);
    }
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public addToCart(): void {
    if (this.product) {
      this._productModel.addToCart(this.product, 1);
      this._snackBar.open(this.language.addedToCart, 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }
  }

  public changeImage(img: string, resetInterval: boolean = false): void {
    if (clearInterval) {
      clearInterval(this._slideInterval);
      this._slideInterval = setInterval(() => this.changeIntervalImage(), 3500);
    }
    if (img !== this.selectedImage) {
      this.imgTransition = true;
      this.srcViewImg = img;
    }
  }

  public changeIntervalImage(): void {
    const currentIndex = this.product.images.indexOf(this.selectedImage);
    const img = this.product.images[currentIndex +1];
    if (img) {
      this.changeImage(img);
    } else {
      this.changeImage(this.product.images[0]);
    }
  }

  public onTransitionEnd(): void {
    this.imgTransition = false;
    this.selectedImage = this.srcViewImg;
  }

  public addedToCart(product: Product | null): void {
    if (this.addToCartCount > 0) {
      if (product) {
        this._productModel.addToCart(product, this.addToCartCount);
        this._snackBar.open(this.languageReplace(this.language.addedToCartName, ['name'], [product.name]), 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      }
    }
  }

  public incrementChange(number: number): void {
    this.addToCartCount = number;
  }

}
