import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from '../data/user';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-login-navigation',
  templateUrl: './login-navigation.component.html',
  styleUrls: ['./login-navigation.component.css']
})
export class LoginNavigationComponent implements OnInit {

  public badLogin = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  @Output() userLoggedIn: EventEmitter<void> = new EventEmitter<void>();

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {
  }

  ngOnInit() {

  }

  login() {
    const user = new User(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value);
    this.userLoggedIn.emit();
    this._authService.authenticate(user, false).subscribe(
      data => {
      },
      err => {
        this.badLogin = true;
      }
    );
  }


}
