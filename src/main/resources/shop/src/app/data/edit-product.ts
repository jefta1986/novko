export class EditProduct {
  id: number;
  name: string;
  nameSr: string;
  code: string;
  enabled: boolean;
  description: string;
  descriptionSr: string;
  brand: string;
  subcategoryName: string;
  amountDin: number;
  amountEuro: number;
  quantity: number;

  constructor(
    id: number,
    name: string,
    nameSr: string,
    code: string,
    enabled: boolean,
    description: string,
    descriptionSr: string,
    brand: string,
    subcategoryName: string,
    amountDin: number,
    amountEuro: number,
    quantity: number,) {
    this.id = id;
    this.name = name;
    this.nameSr = nameSr;
    this.code = code;
    this.enabled = enabled;
    this.description = description;
    this.descriptionSr = descriptionSr;
    this.brand = brand;
    this.subcategoryName = subcategoryName;
    this.amountDin = amountDin;
    this.amountEuro = amountEuro;
    this.quantity = quantity;
  }
}
