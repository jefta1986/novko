import { Component, OnInit } from '@angular/core';
import {User} from '../models/user';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-login-navigation',
  templateUrl: './login-navigation.component.html',
  styleUrls: ['./login-navigation.component.css']
})
export class LoginNavigationComponent implements OnInit {

  public badLogin = false;
  private user = new User();

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {}

  ngOnInit() {

  }

  login() {
    this.user.setPassword = this.loginForm.get('password').value;
    this.user.setUsername = this.loginForm.get('username').value;
    this._authService.authenticate(this.user, false).subscribe(
      data => {},
      err => {
        this.badLogin = true;
      }
    );
  }


}
