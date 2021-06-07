import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {EditProductDialogComponent} from '../dialogs/edit-product-dialog/edit-product-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../data/product';
import {ProductModel} from '../data/models/product.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';
import {UsersModel} from '../data/models/users.model';
import {LoggedUser} from '../data/logged-user';
import {AppConstants} from '../app-constants';
import {MatSlideToggleChange} from '@angular/material/slide-toggle';
import {EditCategoryDialogComponent} from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import {EditUserDialogComponent} from '../dialogs/edit-user-dialog/edit-user-dialog.component';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent extends CommonAbstractComponent implements OnInit {

  public get users(): LoggedUser[] {
    return this._usersModel.users;
  }

  public appConstants: AppConstants = new AppConstants;

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addUser, '/admin-users/registration'),
  ];

  constructor(private _usersModel: UsersModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    this._usersModel.loadUsers();
  }

  public edit(user: LoggedUser) {
    this._dialog.open(EditUserDialogComponent, {
      width: '320px',
      data: user
    });
  }

  public delete(user: LoggedUser) {

  }

  public changeActiveStatus($event: MatSlideToggleChange, user: LoggedUser) {
    const {checked} = $event;
    user.active = checked;
    this._usersModel.changeActiveStatus(user);
  }

}
