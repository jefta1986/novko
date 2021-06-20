import {Product} from './product';
import {Category} from './category';

export class Subcategory {

  public name: string;
  public nameSr: string;
  public id?: number;
  public products?: Product[];
  public category?: Category;

  constructor(name: string,
              nameSr: string,
              id?: number,
              products?: Product[],
              category?: Category) {
    this.id = id;
    this.name = name;
    this.nameSr = nameSr;
    this.products = products;
    this.category = category;
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
  public category: Category;
  public subcategory: Subcategory;

  constructor(category: Category,
              subcategory: Subcategory) {
    this.category = category;
    this.subcategory = subcategory;
  }
}
