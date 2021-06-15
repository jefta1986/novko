export class NewProduct {
  name: string;
  nameSr: string;
  code: string;
  description: string;
  descriptionSr: string;
  brand: string;
  subcategoryName: string;
  amountDin: number;
  amountEuro: number;
  quantity: number;

  constructor(
              name: string,
              nameSr: string,
              code: string,
              description: string,
              descriptionSr: string,
              brand: string,
              subcategoryName: string,
              amountDin: number,
              amountEuro: number,
              quantity: number,) {
    this.name = name;
    this.nameSr = nameSr;
    this.code = code;
    this.description = description;
    this.descriptionSr = descriptionSr;
    this.brand = brand;
    this.subcategoryName = subcategoryName;
    this.amountDin = amountDin;
    this.amountEuro = amountEuro;
    this.quantity = quantity;
  }
}
