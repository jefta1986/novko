import {Component, OnInit, Inject, ChangeDetectorRef} from '@angular/core';
import {FormGroup, Validators, FormControl} from '@angular/forms';
import {ProductService} from '../../services/product.service';
import {Product} from '../../data/product';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ConfirmedValidator} from '../../common/helpers/validation';
import {RegisterUser} from '../../data/register-user';
import {AppConstants} from '../../app-constants';
import {LoggedUser} from '../../data/logged-user';
import {AuthService} from '../../services/auth.service';
import {UsersModel} from '../../data/models/users.model';
import {CommonAbstractComponent} from '../../common/common-abstract-component';
import {CommonLanguageModel} from '../../common/common-language.model';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent extends CommonAbstractComponent implements OnInit {
  public registerUserForm: FormGroup;
  public registerAdminForm: FormGroup;

  public get isAdmin(): boolean {
    return this.user.role === AppConstants.roleAdmin;
  }

  constructor(private _usersModel: UsersModel,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<EditUserDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public user: LoggedUser,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    this.registerAdminForm = new FormGroup({
      username: new FormControl(this.user.username, [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required])
    }, {
      validators: ConfirmedValidator('password', 'repassword')
    });
    this.registerUserForm = new FormGroup({
      username: new FormControl(this.user.username, [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repassword: new FormControl('', [Validators.required]),
      code: new FormControl(this.user.code, [Validators.required]),
      firma: new FormControl(this.user.firma, [Validators.required]),
      grad: new FormControl(this.user.grad, [Validators.required]),
      mb: new FormControl(this.user.mb, [Validators.required]),
      pib: new FormControl(this.user.pib, [Validators.required]),
      rabat: new FormControl(this.user.rabat, [Validators.required, Validators.max(100), Validators.min(0)]),
      ulica: new FormControl(this.user.ulica, [Validators.required]),
    }, {
      validators: ConfirmedValidator('password', 'repassword')
    });
  }

  ngOnInit(): void {
  }

  edit() {
    if (this.isAdmin) {
      const username = this.registerAdminForm.get('username');
      const password = this.registerAdminForm.get('password');

      if (username && username.value !== null &&
        password && password.value !== null) {
        const user = new RegisterUser(username.value, password.value, 'en');
        this._usersModel.edit(user);
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
        this._usersModel.edit(user);
      }
    }
  }

}
