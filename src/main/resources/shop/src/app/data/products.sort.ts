export class ProductsSort {
  page: number;
  size: number;
  sort: string;
  direction: string;
  namePart: string | undefined;
  codePart: string | undefined;

  constructor(page: number,
              size: number,
              sort: string,
              direction: string,
              namePart?: string,
              codePart?: string,) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
    this.namePart = namePart;
    this.codePart = codePart;
  }
}

