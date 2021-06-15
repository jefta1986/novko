import {Component, OnInit, Inject, ChangeDetectorRef, OnDestroy} from '@angular/core';
import {CategoryService} from 'src/app/services/category.service';
import {Validators, FormGroup, FormControl} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Subcategory, SubcategoryEdit} from '../../data/subcategory';
import {CommonAbstractComponent} from '../../common/common-abstract-component';
import {CommonLanguageModel} from '../../common/common-language.model';
import {CategoriesModel} from '../../data/models/categories.model';

@Component({
  selector: 'app-edit-subcategory-dialog',
  templateUrl: './edit-subcategory-dialog.component.html',
  styleUrls: ['./edit-subcategory-dialog.component.css']
})
export class EditSubcategoryDialogComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  editSubcategoryForm: FormGroup;

  constructor(private _categoriesModel: CategoriesModel,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<EditSubcategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public subcategory: SubcategoryEdit,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    this.editSubcategoryForm = new FormGroup({
      name: new FormControl(this.subcategory.subcategory.name, [Validators.required]),
      nameSr: new FormControl(this.subcategory.subcategory.nameSr, [Validators.required]),
    });
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public editSubcategory(): void {
    const subcategory = new Subcategory(
      this.editSubcategoryForm.get('name')?.value,
      this.editSubcategoryForm.get('nameSr')?.value,
      this.subcategory.subcategory.id);

    this._categoriesModel.editSubcategory(subcategory, this.subcategory.category.name);
  }

}
