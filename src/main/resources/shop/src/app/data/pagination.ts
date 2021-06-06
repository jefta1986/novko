import {Order} from './order';

export class Pagination {
  content: Order[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: {
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    unpaged: boolean;
  };
  size: number;
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  totalElements: number;
  totalPages: number;

  constructor(content: any[],
              empty: boolean,
              first: boolean,
              last: boolean,
              number: number,
              numberOfElements: number,
              pageable: {
                offset: number;
                pageNumber: number;
                pageSize: number;
                paged: boolean;
                sort: {
                  empty: boolean;
                  sorted: boolean;
                  unsorted: boolean;
                };
                unpaged: boolean;
              },
              size: number,
              sort: {
                empty: boolean;
                sorted: boolean;
                unsorted: boolean;
              },
              totalElements: number,
              totalPages: number) {
    this.content = content;
    this.empty = empty;
    this.first = first;
    this.last = last;
    this.number = number;
    this.numberOfElements = numberOfElements;
    this.pageable = pageable;
    this.size = size;
    this.sort = sort;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
  }
}
