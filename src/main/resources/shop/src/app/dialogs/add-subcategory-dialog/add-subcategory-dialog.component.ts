import {Component, OnInit} from '@angular/core';
import {Subcategory} from 'src/app/data/subcategory';
import {FormGroup, Validators, FormControl} from '@angular/forms';
import {CategoryService} from 'src/app/services/category.service';
import {Category} from 'src/app/data/category';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-subcategory-dialog',
  templateUrl: './add-subcategory-dialog.component.html',
  styleUrls: ['./add-subcategory-dialog.component.css']
})
export class AddSubcategoryDialogComponent implements OnInit {
  subcategoryForm: FormGroup;
  categories: Category[] = [];

  constructor(private _categoryService: CategoryService,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<AddSubcategoryDialogComponent>,) {
    this.subcategoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe((res) => {
      this.categories = res;
    });
  }

  addSubcategory(subcategoryForm: FormGroup) {
    const category = new Subcategory(this.subcategoryForm.get('name')?.value);
    this._categoryService.addSubcategory(category, this.subcategoryForm.get('category')?.value)
      .subscribe(res => {
      }, err => {
        this._snackBar.open('Something went wrong,try again!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }, () => {
        this._snackBar.open('Subcategory added!', 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
        this._dialogRef.close();
      });
  }
}
