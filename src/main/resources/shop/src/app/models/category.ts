import {Subcategory} from './subcategory';

export class Category {
  public id: number | null;
  public name: string;
  public subcategories: Subcategory[] | null;

  constructor(name: string,
              id?: number,
              subcategories?: Subcategory[]) {
    this.id = id || null;
    this.name = name;
    this.subcategories = subcategories || null;
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
