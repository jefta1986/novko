import {PaginationRequest} from './pagination';


export class ProductsSort {
  page: number;
  size: number;
  sort: string;
  direction: string;
  active: boolean;
  namePart: string | undefined;
  codePart: string | undefined;

  constructor(page: number,
              size: number,
              sort: string,
              direction: string,
              active: boolean,
              namePart?: string,
              codePart?: string,) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
    this.active = active;
    this.namePart = namePart;
    this.codePart = codePart;
  }
}

