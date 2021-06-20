import {Component, OnInit, ChangeDetectorRef, OnDestroy} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonAbstractComponent} from '../../common/common-abstract-component';
import {CommonLanguageModel} from '../../common/common-language.model';
import {CategoriesModel} from '../../data/models/categories.model';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  constructor(private _categoriesModel: CategoriesModel,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<ConfirmDialogComponent>,
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

  public close(confirm: boolean = false): void {
    this._dialogRef.close(confirm);
  }

}
