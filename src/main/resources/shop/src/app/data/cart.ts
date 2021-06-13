import {Product} from './product';

export class Cart {
  id: number;
  amountDin: number;
  amountEuro: number;
  product: Product;
  quantity: number;

  constructor(id: number,
              amountDin: number,
              amountEuro: number,
              product: Product,
              quantity: number) {
    const {
      id: productId,
      name,
      nameSr,
      code,
      description,
      descriptionSr,
      enabled,
      brand,
      subcategory,
      amountDin: productAmountDin,
      amountEuro: productAmountEuro,
      quantity: productQuantity,
      orderQuantity,
      images
    } = product;
    this.id = id;
    this.amountDin = amountDin;
    this.amountEuro = amountEuro;
    this.quantity = quantity;
    this.product = new Product(
      productId,
      name,
      nameSr,
      code,
      description,
      descriptionSr,
      enabled,
      brand,
      subcategory,
      productAmountDin,
      productAmountEuro,
      productQuantity,
      orderQuantity,
      images);
  }
}
