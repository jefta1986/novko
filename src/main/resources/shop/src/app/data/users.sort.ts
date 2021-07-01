export class UsersSort {
  page: number;
  size: number;
  sort: string;
  direction: string;
  emailPart: string | undefined;
  mbPart: string | undefined;
  pibPart: string | undefined;

  constructor(page: number,
              size: number,
              sort: string,
              direction: string,
              emailPart?: string,
              mbPart?: string,
              pibPart?: string,) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
    this.emailPart = emailPart;
    this.mbPart = mbPart;
    this.pibPart = pibPart;
  }
}

