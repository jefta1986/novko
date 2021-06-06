import {Component, OnInit} from '@angular/core';
import {Category} from 'src/app/data/category';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {CategoryService} from 'src/app/services/category.service';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-addcategorydialog',
  templateUrl: './add-category-dialog.component.html',
  styleUrls: ['./add-category-dialog.component.css']
})
export class AddCategoryDialogComponent implements OnInit {
  categoryForm: FormGroup;

  constructor(private _categoryService: CategoryService,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<AddCategoryDialogComponent>) {
    this.categoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
  }

  addCategory(categoryForm: FormGroup) {
    const category = new Category(this.categoryForm.get('name')?.value);
    this._categoryService.addCategory(category).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open('Category added!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this._dialogRef.close();
    });
  }

}
