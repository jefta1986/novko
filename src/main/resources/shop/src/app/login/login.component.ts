import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user = new User();
  static badLogin;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {
      LoginComponent.badLogin = false;
   }

  ngOnInit() {

  }

  login() {
    this.user.setPassword = this.loginForm.get('password').value;
    this.user.setUsername = this.loginForm.get('username').value;
    this._authService.authenticate(this.user);
  }
  
  get staticBadLogin() {
    return LoginComponent.badLogin;
  }
}
