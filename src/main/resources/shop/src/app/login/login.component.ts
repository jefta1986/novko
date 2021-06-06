import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {User} from '../data/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public badLogin = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private formBuilder: FormBuilder,
              private _authService: AuthService) {}

  ngOnInit(): void {

  }

  login(): void {
    const user = new User(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value);
    this._authService.authenticate(user).subscribe(
      data => {},
      err => {
        this.badLogin = true;
      }
    );
  }

}
