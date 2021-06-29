import {Moment} from 'moment';

export class OrdersSort {
  page: number;
  size: number;
  sort: string;
  direction: string;
  status: boolean | null;
  fromDate: Moment | null | undefined;
  toDate: Moment | null | undefined;

  constructor(page: number,
              size: number,
              sort: string,
              direction: string,
              status: boolean | null,
              fromDate?: Moment,
              toDate?: Moment) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
    this.status = status;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }
}

