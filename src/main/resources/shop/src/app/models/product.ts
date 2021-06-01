import {Subcategory} from './subcategory';

export class Product {
  id: number;
  name: string;
  code: string;
  description: string;
  descriptionSr: string;
  enabled: boolean;
  brand: string;
  subcategory: Subcategory;
  amountDin: number;
  amountEuro: number;
  quantity: number;
  orderQuantity: number;
  images: string[];

  constructor(id: number,
              name: string,
              code: string,
              description: string,
              descriptionSr: string,
              enabled: boolean,
              brand: string,
              subcategory: Subcategory,
              amountDin: number,
              amountEuro: number,
              quantity: number,
              orderQuantity?: number,
              images?: string[]) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.descriptionSr = descriptionSr;
    this.enabled = enabled;
    this.brand = brand;
    this.subcategory = subcategory;
    this.amountDin = amountDin;
    this.amountEuro = amountEuro;
    this.quantity = quantity;
    this.orderQuantity = 0;
    this.images = images || [];
  }
}

export class ProductBackend {
  id: number;
  name: string;
  code: string;
  description: string;
  descriptionSr: string;
  enabled: boolean;
  brand: string;
  amountDin: number;
  amountEuro: number;
  quantity: number;
  orderQuantity: number;
  images: string[];

  constructor(id: number,
              name: string,
              code: string,
              description: string,
              descriptionSr: string,
              enabled: boolean,
              brand: string,
              amountDin: number,
              amountEuro: number,
              quantity: number,
              orderQuantity: number,
              images: string[]) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.descriptionSr = descriptionSr;
    this.enabled = enabled;
    this.brand = brand;
    this.amountDin = amountDin;
    this.amountEuro = amountEuro;
    this.quantity = quantity;
    this.orderQuantity = 0;
    this.images = images;
  }
}

export class ProductCount {
  public product: Product | null;
  public count: number;

  constructor(product: Product | null,
              count: number) {
    this.product = product;
    this.count = count;
  }
}
