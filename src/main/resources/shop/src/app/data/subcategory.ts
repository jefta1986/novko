import {Product} from './product';

export class Subcategory {

  public name: string;
  public nameSr: string;
  public id?: number;
  public products?: Product[];

  constructor(name: string,
              nameSr: string,
              id?: number,
              products?: Product[]) {
    this.id = id;
    this.name = name;
    this.nameSr = nameSr;
    this.products = products;
  }

  public get getName(): string {
    return this.name;
  }

  public get getID() {
    return this.id;
  }

  public get getProducts(): Product[] | undefined {
    return this.products;
  }

  public set setName(name: string) {
    this.name = name;
  }

  public set setProducts(products: []) {
    this.products = products;
  }

  public set setID(id: number) {
    this.id = id;
  }

}

export class SubcategoryEdit {
  public categoryName: string;
  public subcategory: Subcategory;

  constructor(categoryName: string,
              subcategory: Subcategory,) {
    this.categoryName = categoryName;
    this.subcategory = subcategory;
  }
}
