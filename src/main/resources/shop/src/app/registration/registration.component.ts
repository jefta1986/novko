import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';
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
  private selected = 'ROLE_USER';
  private registrationForm:FormGroup;

  constructor(private formBuilder: FormBuilder, private _authService: AuthService) {
    this.registrationForm = new FormGroup({
      username: new FormControl('', [Validators.required,Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required])
    });
    this.registrationForm.setValidators(this.passwordMatchValidator())
   }

  ngOnInit() {
  }

  register(registrationForm:FormGroup) {
    this.user.setPassword = this.registrationForm.get('password').value;
    this.user.setUsername = this.registrationForm.get('username').value;
    this._authService.register(this.user,this.selected);
  }

  passwordMatchValidator() : ValidatorFn{
    return (group: FormGroup): ValidationErrors => {
       const password = group.controls['password'];
       const repassword = group.controls['repassword'];
       if (password.value !== repassword.value) {
          repassword.setErrors({notEquivalent: true});
       } else {
          repassword.setErrors(null);
       }
       return;
 };
}
}
