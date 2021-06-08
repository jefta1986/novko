import {Subcategory} from './subcategory';

export class Category {
  public id: number | null;
  public name: string;
  public nameSr: string;
  public subcategories: Subcategory[] | [];

  constructor(name: string,
              nameSr: string,
              id?: number | null,
              subcategories?: Subcategory[] | []) {
    this.id = id || null;
    this.nameSr = nameSr;
    this.name = name;
    this.subcategories = subcategories || [];
  }

  public get getName(): string {
    return this.name;
  }

  public get getID() {
    return this.id;
  }

  public get getSubcategories(): Subcategory[] | null {
    return this.subcategories;
  }

  public set setName(name: string) {
    this.name = name;
  }

  public set setSubcategories(subcategories: []) {
    this.subcategories = subcategories;
  }

  public set setID(id: number) {
    this.id = id;
  }

}
