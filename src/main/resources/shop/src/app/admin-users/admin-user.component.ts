import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';
import {UsersModel} from '../data/models/users.model';
import {LoggedUser} from '../data/logged-user';
import {AppConstants} from '../app-constants';
import {MatSlideToggleChange} from '@angular/material/slide-toggle';
import {EditUserDialogComponent} from '../dialogs/edit-user-dialog/edit-user-dialog.component';
import {NoItem} from '../common/common-language.interface';
import {ConfirmDialogComponent} from '../dialogs/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get users(): LoggedUser[] {
    return this._usersModel.users;
  }

  public appConstants: AppConstants = new AppConstants;

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addUser, '/admin-users/registration'),
  ];

  public noItemType: NoItem = NoItem.users;

  constructor(private _usersModel: UsersModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public edit(user: LoggedUser) {
    this._dialog.open(EditUserDialogComponent, {
      width: '320px',
      data: user
    });
  }

  public delete(user: LoggedUser) {
    const dialogRef = this._dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this._usersModel.deleteUser(user);
      }
    });
  }

  public changeActiveStatus($event: MatSlideToggleChange, user: LoggedUser) {
    const {checked} = $event;
    user.active = checked;
    this._usersModel.changeActiveStatus(user);
  }

}
