import {Injectable} from '@angular/core';
import {Product} from '../product';
import {ProductService} from '../../services/product.service';
import {Utils} from '../../app.utils';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable()
export class ProductModel {
  private _products: Product[] = [];
  private _currentProduct: Product = this._products[0];
  private errorLoading = false;

  public get product(): Product {
    return this._currentProduct;
  }

  public get products(): Product[] {
    return this._products;
  }

  public get productsInCart(): Product[] {
    return this._products.filter(prod => prod.orderQuantity > 0);
  }

  constructor(private productService: ProductService,
              private _snackBar: MatSnackBar) {
    this.loadProducts();
  }

  public addToCart(product: Product | null, count: number) {
    if (product) {
      const productById = this._products.find(item => item.id === product.id);
      if (productById) {
        productById.orderQuantity = productById.orderQuantity + count;
        Utils.syncCart(this._products.filter(prod => prod.orderQuantity > 0));
      }
    }
  }

  public removeFromCart(product: Product) {
    const productById = this._products.find(item => item.id === product.id);
    if (productById) {
      productById.orderQuantity = 0;
      Utils.syncCart(this._products.filter(prod => prod.orderQuantity > 0));
    }
  }

  public deleteProduct(product: Product) {
    this.productService.deleteProduct(product.id).subscribe(() => {
      this._products = this._products.filter(p => p.id !== product.id);
      this._snackBar.open(`Product ${product.name} deleted!`, 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    }, () => {
      this._snackBar.open('Something went wrong, try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    });
  }

  public loadProducts(): void {
    this.productService.getAllProductsWithImages().subscribe(
      (result) => {
        this._products = result.map(({
                                       id,
                                       name,
                                       code,
                                       description,
                                       descriptionSr,
                                       enabled,
                                       brand,
                                       subcategory,
                                       amountDin,
                                       amountEuro,
                                       quantity,
                                       orderQuantity,
                                       images
                                     }) => new Product(id,
          name,
          code,
          description,
          descriptionSr,
          enabled,
          brand,
          subcategory,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images));
        const cart = Utils.getProductsFromCart();
        if (cart.length > 0) {
          for (let i = 0; i < cart.length; i++) {
            const cartedProduct = this._products.find(product => product.id === cart[i].id);
            if (cartedProduct) {
              cartedProduct.orderQuantity = cartedProduct.orderQuantity + cart[i].orderQuantity;
            }
          }
        }
      },
      (err) => this.errorLoading = true);
  }

  public loadProductByCode(code: string): void {
    this.productService.getProductByCode(code).subscribe(
      (product: Product) => {
        const {
          id,
          name,
          code: resProductCode,
          description,
          descriptionSr,
          enabled,
          brand,
          subcategory,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images
        } = product;

        this._currentProduct = new Product(id,
          name,
          resProductCode,
          description,
          descriptionSr,
          enabled,
          brand,
          subcategory,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images);
        const cart = Utils.getProductsFromCart();
        if (cart.length > 0) {
          const cartedProduct = cart.find((cartedProduct: Product) => cartedProduct.id === id);
          if (cartedProduct) {
            this._currentProduct.orderQuantity = this._currentProduct.orderQuantity + cartedProduct.orderQuantity;
          }
        }
      },
      (err: HttpErrorResponse): Observable<HttpErrorResponse> => {
        this.errorLoading = true;
        return throwError(err);
      });
  }

  public loadProductsBySubcategory(name: string): void {
    this.productService.getProductsFromSubcategories(name).subscribe(
      (result) => {
        this._products = result.map(({
                                       id,
                                       name,
                                       code,
                                       description,
                                       descriptionSr,
                                       enabled,
                                       brand,
                                       subcategory,
                                       amountDin,
                                       amountEuro,
                                       quantity,
                                       orderQuantity,
                                       images
                                     }) => new Product(id,
          name,
          code,
          description,
          descriptionSr,
          enabled,
          brand,
          subcategory,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images));
        const cart = Utils.getProductsFromCart();
        if (cart.length > 0) {
          for (let i = 0; i < cart.length; i++) {
            const cartedProduct = this._products.find(product => product.id === cart[i].id);
            if (cartedProduct) {
              cartedProduct.orderQuantity = cartedProduct.orderQuantity + cart[i].orderQuantity;
            }
          }
        }
      },
      (err) => this.errorLoading = true);
  }
}
