import {Component, OnInit, Inject, ChangeDetectorRef, OnDestroy} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {CategoryService} from '../../services/category.service';
import {Category} from '../../data/category';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommonAbstractComponent} from '../../common/common-abstract-component';
import {CommonLanguageModel} from '../../common/common-language.model';
import {CategoriesModel} from '../../data/models/categories.model';

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  editCategoryForm: FormGroup;

  constructor(private _categoriesModel: CategoriesModel,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<EditCategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public category: Category,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    this.editCategoryForm = new FormGroup({
      name: new FormControl(this.category.name, [Validators.required]),
      nameSr: new FormControl(this.category.nameSr, [Validators.required]),
    });
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public editCategory(): void {
    const category = new Category(this.editCategoryForm.get('name')?.value, this.editCategoryForm.get('nameSr')?.value, this.category.id)
    this._categoriesModel.editCategory(category);
    this._dialogRef.close();
  }

}
