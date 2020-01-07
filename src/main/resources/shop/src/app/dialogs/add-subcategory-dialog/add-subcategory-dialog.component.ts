import { Component, OnInit } from '@angular/core';
import { Subcategory } from 'src/app/models/subcategory';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { MatSnackBar, MatDialogRef } from '@angular/material';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-add-subcategory-dialog',
  templateUrl: './add-subcategory-dialog.component.html',
  styleUrls: ['./add-subcategory-dialog.component.css']
})
export class AddSubcategoryDialogComponent implements OnInit {

  private subcategory = new Subcategory();
  private subcategoryForm: FormGroup;

  constructor(private _categoryService: CategoryService, private _snackBar: MatSnackBar, private _dialogRef: MatDialogRef<AddSubcategoryDialogComponent>, ) {
    this.subcategoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
  }

  addSubcategory(subcategoryForm: FormGroup) {
    console.log(subcategoryForm);
    this._snackBar.open("Subcategory added!", 'Success', {
      duration: 4000,
      panelClass: ['my-snack-bar']
    });
    this._dialogRef.close();
    // this.subcategory.setName = this.subcategoryForm.get('name').value;
    // this._categoryService.addCategory(this.subcategory).subscribe(res=>{},err=>{
    //   this._snackBar.open("Something went wrong,try again!", 'Error', {
    //     duration: 4000,
    //     panelClass: ['my-snack-bar-error']
    //   });
    // },()=>{
    //   this._snackBar.open("Category added!", 'Success', {
    //     duration: 4000,
    //     panelClass: ['my-snack-bar']
    //   });
    //   this._dialogRef.close();
    // });
  }
}
