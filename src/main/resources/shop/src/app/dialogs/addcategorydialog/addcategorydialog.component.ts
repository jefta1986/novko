import {Component, OnInit} from '@angular/core';
import {Category} from 'src/app/models/category';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {CategoryService} from 'src/app/services/category.service';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-addcategorydialog',
  templateUrl: './addcategorydialog.component.html',
  styleUrls: ['./addcategorydialog.component.css']
})
export class AddcategorydialogComponent implements OnInit {

  private category = new Category();
  categoryForm: FormGroup;

  constructor(private _categoryService: CategoryService,
              private _snackBar: MatSnackBar,
              private _dialogRef: MatDialogRef<AddcategorydialogComponent>) {
    this.categoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
  }

  addCategory(categoryForm: FormGroup) {
    this.category.setName = this.categoryForm.get('name').value;
    this._categoryService.addCategory(this.category).subscribe(res => {
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
