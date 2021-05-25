import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {User} from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

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
    this._authService.authenticate(this.user).subscribe(
      data => {},
      err => {
        this.badLogin = true;
      }
    );
  }

}
