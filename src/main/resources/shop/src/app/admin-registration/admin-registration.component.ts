import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {ConfirmedValidator} from '../common/helpers/validation';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AppConstants} from '../app-constants';
import {RegisterUser} from '../data/register-user';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.css']
})
export class AdminRegistrationComponent extends CommonAbstractComponent implements OnInit {
  public registerUserForm: FormGroup;
  public registerAdminForm: FormGroup;

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              private formBuilder: FormBuilder,
              private _authService: AuthService) {
    super(cdr, commonLanguageModel);
    this.registerAdminForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required])
    }, {
      validators: ConfirmedValidator('password', 'repassword')
    });
    this.registerUserForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required]),
      code: new FormControl('', [Validators.required]),
      firma: new FormControl('', [Validators.required]),
      grad: new FormControl('', [Validators.required]),
      mb: new FormControl('', [Validators.required]),
      pib: new FormControl('', [Validators.required]),
      rabat: new FormControl('', [Validators.required, Validators.max(100), Validators.min(0)]),
      ulica: new FormControl('', [Validators.required]),
    }, {
      validators: ConfirmedValidator('password', 'repassword')
    });
  }

  ngOnInit() {
  }

  register(isAdmin: boolean) {
    const registerAdminForm = this.registerAdminForm.get('password');
    debugger;
    if (isAdmin) {
      const username = this.registerAdminForm.get('username');
      const password = this.registerAdminForm.get('password');

      if (username && username.value !== null &&
        password && password.value !== null) {
        const user = new RegisterUser(username.value, password.value, 'en');
        this._authService.register(user, AppConstants.roleAdmin);
      }
    } else {
      const username = this.registerUserForm.get('username');
      const password = this.registerUserForm.get('password');
      const code = this.registerUserForm.get('code');
      const firma = this.registerUserForm.get('firma');
      const grad = this.registerUserForm.get('grad');
      const mb = this.registerUserForm.get('mb');
      const pib = this.registerUserForm.get('pib');
      const rabat = this.registerUserForm.get('rabat');
      const ulica = this.registerUserForm.get('ulica');

      if (username && username.value !== null &&
        password && password.value !== null &&
        code && code.value !== null &&
        firma && firma.value !== null &&
        grad && grad.value !== null &&
        mb && mb.value !== null &&
        pib && pib.value !== null &&
        rabat && rabat.value !== null &&
        ulica && ulica.value !== null) {
        const user = new RegisterUser(username.value,
          password.value,
          'en',
          code.value,
          firma.value,
          grad.value,
          mb.value,
          pib.value,
          rabat.value,
          AppConstants.roleUser,
          ulica.value);
        this._authService.register(user, AppConstants.roleUser);
      }
    }
  }

}
