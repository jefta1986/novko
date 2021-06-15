import {Injectable} from '@angular/core';
import {Product} from '../product';
import {ProductService} from '../../services/product.service';
import {Utils} from '../../app.utils';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable, Subscription, throwError} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Injectable()
export class ProductModel {
  private _products: Product[] = [];
  private _cartedProducts: Product[] = [];
  private _currentProduct: Product = this._products[0];
  private errorLoading = false;

  public get product(): Product {
    return this._currentProduct;
  }

  public get products(): Product[] {
    return this._products;
  }

  public get productsInCart(): Product[] {
    return this._cartedProducts;
  }

  constructor(private _productService: ProductService,
              private _authService: AuthService,
              private _snackBar: MatSnackBar,
              private _router: Router) {
    this.loadProducts();
  }

  public addToCart(product: Product | null, count: number) {
    if (product) {
      const productById = this._products.find(item => item.id === product.id);
      const productInCart = this._cartedProducts.find(item => item.id === product.id);
      if (productById) {
        if (productInCart) {
          productInCart.orderQuantity = productInCart.orderQuantity + count;
        } else {
          product.orderQuantity = product.orderQuantity + count;
          this._cartedProducts.push(product);
        }
        Utils.syncCart(this._cartedProducts);
      }
    }
  }

  public changeCartNumber(count: number, product: Product) {
    const productInCart = this._cartedProducts.find(item => item.id === product.id);
    if (productInCart) {
      productInCart.orderQuantity = count;
      Utils.syncCart(this._cartedProducts);
    }
  }

  public removeFromCart(product: Product) {
    const productById = this._cartedProducts.find(item => item.id === product.id);
    if (productById) {
      this._cartedProducts = this._cartedProducts.filter(item => item.id !== productById.id);
      Utils.syncCart(this._cartedProducts);
    }
  }

  public deleteProductByCode(product: Product) {
    this._productService.deleteProductByCode(product.code).subscribe(() => {
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
    this._productService.getAllProductsWithImages().subscribe(
      (result) => {
        this._products = result.map(({
                                       id,
                                       name,
                                       nameSr,
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
          nameSr,
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
        ));
        this._cartedProducts = Utils.getProductsFromCart();
      },
      (err) => this.errorLoading = true);
  }

  public loadProductByCode(code: string): Observable<Product> {
    return new Observable<Product>(subscriber => {
      this._productService.getProductByCode(code).subscribe(
        (product: Product) => {
          const {
            id,
            name,
            nameSr,
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
            nameSr,
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
          subscriber.next(this._currentProduct);
        },
        (err: HttpErrorResponse): Observable<HttpErrorResponse> => {
          this.errorLoading = true;
          return throwError(err);
        });
    });
  }

  public loadProductsBySubcategory(name: string): void {
    this._productService.getProductsFromSubcategories(name).subscribe(
      (result) => {
        this._products = result.map(({
                                       id,
                                       name,
                                       nameSr,
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
          nameSr,
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

  public setEditProduct(product: Product): void {
    this._currentProduct = product;
  }

  public order(products: Product[]): void {
    let params: any = {};
    products.map((product) => {
      const newProduct = {
        [product.code]: product.orderQuantity
      };
      params = {...params, ...newProduct};
    });
    const user = this._authService.user?.username;
    if (user) {
      this._productService.order(params, this._authService.user?.username).subscribe((res) => {
        if (res.status === true) {
          this._cartedProducts = [];
          Utils.syncCart([]);
          this._router.navigate(['/home']);
          this._snackBar.open(`Products ordered!`, 'Success', {
            duration: 4000,
            panelClass: ['my-snack-bar']
          });
        } else {
          this._snackBar.open('Something went wrong, try again!', 'Error', {
            duration: 4000,
            panelClass: ['my-snack-bar-error']
          });
        }
      });
    }
  }
}
