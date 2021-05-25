export class Utils {

  static readonly cartArray = 'cartArray';

  static getProductsFromCart() {
    return JSON.parse(localStorage.getItem(Utils.cartArray));
  }

  static syncCart(products) {
    localStorage.setItem(Utils.cartArray, JSON.stringify(products));
  }

}
