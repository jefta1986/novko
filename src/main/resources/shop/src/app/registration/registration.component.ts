import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private user = new User();

  registrationForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    repassword: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required,Validators.email]),
    role: new FormControl('', [Validators.required])

  });

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {
   }

  ngOnInit() {
  }

  register(registrationForm:FormGroup) {
    console.log(registrationForm);
    this.user.setPassword = this.registrationForm.get('password').value;
    this.user.setUsername = this.registrationForm.get('username').value;
    //this._authService.authenticate(this.user);
  }

}
