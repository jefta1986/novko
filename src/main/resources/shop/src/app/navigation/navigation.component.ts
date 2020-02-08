import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Utils } from '../app.utils';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  adminLoggedIn = false;
  userLoggedIn = false;
  numberOfItemsInCart = 0;

  constructor(private _authService: AuthService) { }

  ngOnInit() {
    this.adminLoggedIn = AuthService.isAuthenticatedAdmin();
    this.userLoggedIn = AuthService.isAuthenticatedUser();
    this.getNumberOfItemsInCart();
  }

  getNumberOfItemsInCart() {
    this.numberOfItemsInCart = 0;
    if (localStorage.getItem(Utils.cartArray) != null) {
      let array = JSON.parse(localStorage.getItem(Utils.cartArray));
      array.forEach(element => {
        this.numberOfItemsInCart++;
      });
      console.log(this.numberOfItemsInCart);
    }

  }

  logout() {
    this._authService.logout();
  }

}
