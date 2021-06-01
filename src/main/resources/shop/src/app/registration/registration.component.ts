import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl, AbstractControl, ValidatorFn, ValidationErrors} from '@angular/forms';
import {User} from '../models/user';
import {AuthService} from '../services/auth.service';
import {ConfirmedValidator} from '../common/helpers/validation';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  selected = 'ROLE_USER';
  registrationForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {
    this.registrationForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required])
    }, {
      validators: ConfirmedValidator('password', 'repassword')
    });
  }

  ngOnInit() {
  }

  register() {
    const username = this.registrationForm.get('username');
    const password = this.registrationForm.get('password');
    if (username && username.value !== null && password && password.value !== null) {
      const user = new User(username.value, password.value);
      this._authService.register(user, this.selected);
    }
  }

}
