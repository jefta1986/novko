import {Product} from './data/product';

export class Utils {

  static readonly cartArray = 'cartArray';

  static getProductsFromCart(): Product[] {
    const cart = localStorage.getItem(Utils.cartArray);
    if (cart !== null) {
      return JSON.parse(cart);
    } else {
      return [];
    }
  }

  static syncCart(products: Product[]) {
    localStorage.setItem(Utils.cartArray, JSON.stringify(products));
  }

}
