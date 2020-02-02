import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { MatSnackBar, MatDialogRef } from '@angular/material';
import { MAT_DIALOG_DATA } from '@angular/material'
import { Category } from '../../models/category';

@Component({
  selector: 'app-edit-category-dialog',
  templateUrl: './edit-category-dialog.component.html',
  styleUrls: ['./edit-category-dialog.component.css']
})
export class EditCategoryDialogComponent implements OnInit {

  private editCategoryForm: FormGroup;

  constructor(private _categoryService: CategoryService,
    private _snackBar: MatSnackBar,
    private _dialogRef: MatDialogRef<EditCategoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public category: Category) {

      this.editCategoryForm = new FormGroup({
        name: new FormControl('', [Validators.required]),
      });
      
  }

  ngOnInit() {
  }

  editCategory(categoryForm: FormGroup) {
    this.category.name = this.editCategoryForm.get('name').value;
    console.log(this.category);
    this._categoryService.editCategory(this.category).subscribe(res => { }, err => {
      this._snackBar.open("Something went wrong,try again!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open("Category edited!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this._dialogRef.close();
    });
  }

}
