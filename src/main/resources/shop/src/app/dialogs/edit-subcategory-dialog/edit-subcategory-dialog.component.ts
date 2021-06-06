import {Component, OnInit, Inject} from '@angular/core';
import {CategoryService} from 'src/app/services/category.service';
import {Validators, FormGroup, FormControl} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {SubcategoryEdit} from '../../data/subcategory';

@Component({
  selector: 'app-edit-subcategory-dialog',
  templateUrl: './edit-subcategory-dialog.component.html',
  styleUrls: ['./edit-subcategory-dialog.component.css']
})
export class EditSubcategoryDialogComponent implements OnInit {

  editSubcategoryForm: any;

  constructor(private _categoryService: CategoryService,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<EditSubcategoryDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public subcategoryEdit: SubcategoryEdit) {

    this.editSubcategoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
    });

  }

  ngOnInit(): void {
  }

  editSubcategory() {
    const oldSubcategory = this.subcategoryEdit.subcategory;
    const categoryName = this.subcategoryEdit.categoryName;
    const newName = this.editSubcategoryForm.get('name')?.value;

    this._categoryService.editSubcategory(oldSubcategory, categoryName, newName).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open('Subcategory edited!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this._dialogRef.close();
    });
  }

}
