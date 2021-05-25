export class Product {
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
  images: [];

  constructor(id,
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
              images) {
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
