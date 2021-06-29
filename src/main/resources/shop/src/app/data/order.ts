import {LoggedUser} from './logged-user';
import {Timestamp} from 'rxjs';
import {Cart} from './cart';

export class Order {
  user: LoggedUser;
  id: number;
  orderDate: string;
  quantity: number;
  status: boolean;
  totalAmountDin: number;
  totalAmountEuro: number;
  carts: Cart[];

  constructor(user: LoggedUser,
              id: number,
              orderDate: string,
              quantity: number,
              status: boolean,
              totalAmountDin: number,
              totalAmountEuro: number,
              carts: Cart[]) {
    const {
      active,
      code,
      firma,
      grad,
      id: userId,
      language,
      mb,
      pib,
      rabat,
      role,
      ulica,
      username
    } = user;
    this.user = new LoggedUser(active,
      code,
      firma,
      grad,
      userId,
      language,
      mb,
      pib,
      rabat,
      role,
      ulica,
      username);
    this.id = id;
    this.orderDate = orderDate;
    this.quantity = quantity;
    this.status = status;
    this.totalAmountDin = totalAmountDin;
    this.totalAmountEuro = totalAmountEuro;
    this.carts = carts.map(({
                              id: cartId,
                              amountDin,
                              amountEuro,
                              product,
                              quantity
                            }) => {
      return new Cart(cartId,
        amountDin,
        amountEuro,
        product,
        quantity);
    });
  }
}
