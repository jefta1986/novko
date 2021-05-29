import {Injectable} from '@angular/core';
import {Product} from './product';
import {ProductService} from '../services/product.service';
import {Utils} from '../app.utils';

@Injectable()
export class ProductModel {
  private _products: Product[] = [];
  private _currentProduct: Product = null;
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

  constructor(private productService: ProductService) {
    this.loadProducts();
  }

  public addToCart(product: Product, count: number) {
    const productById = this._products.find(item => item.id === product.id);
    productById.orderQuantity = productById.orderQuantity + count;
    Utils.syncCart(this._products.filter(prod => prod.orderQuantity > 0));
  }

  public removeFromCart(product: Product) {
    const productById = this._products.find(item => item.id === product.id);
    productById.orderQuantity = 0;
    Utils.syncCart(this._products.filter(prod => prod.orderQuantity > 0));
  }

  protected loadProducts(): void {
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
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images));
        const cart = JSON.parse(localStorage.getItem(Utils.cartArray));
        if (cart && cart.length > 0) {
          for (let i = 0; i < cart.length; i++) {
            const cartedProduct = this._products.find(product => product.id === cart[i].id);
            cartedProduct.orderQuantity = cartedProduct.orderQuantity + cart[i].orderQuantity;
          }
        }
      },
      (err) => this.errorLoading = true);
  }

  public loadProductByCode(code: string): void {
    this.productService.getProductByCode(code).subscribe(
      (result) => {
        const {
          id,
          name,
          code: resProductCode,
          description,
          descriptionSr,
          enabled,
          brand,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images
        } = result;

        this._currentProduct = new Product(id,
          name,
          resProductCode,
          description,
          descriptionSr,
          enabled,
          brand,
          amountDin,
          amountEuro,
          quantity,
          orderQuantity,
          images);
        const cart = JSON.parse(localStorage.getItem(Utils.cartArray));
        if (cart && cart.length > 0) {
            const cartedProduct = cart.find(({id: cartedId}) => cartedId === id);
            if (cartedProduct) {
              this._currentProduct.orderQuantity = this._currentProduct.orderQuantity + cartedProduct.orderQuantity;
            }
        }
      },
      (err) => this.errorLoading = true);
  }
}
