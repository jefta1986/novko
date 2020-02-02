import { Component, OnInit } from '@angular/core';
import { Category } from '../models/category';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { CategoryService } from '../services/category.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { AddcategorydialogComponent } from '../dialogs/addcategorydialog/addcategorydialog.component';
import { EditCategoryDialogComponent } from '../dialogs/edit-category-dialog/edit-category-dialog.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  private allCategories;

  constructor(private _categoryService: CategoryService, private _dialog: MatDialog, private _snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(res => {
      this.allCategories = res;
    });
  }

  openDialog(): void {
    const dialogRef = this._dialog.open(AddcategorydialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  edit(categoryName: string) {
    var categoryToBeEdited = {};
    this.allCategories.forEach(element => {
      if (element.name == categoryName) {
        categoryToBeEdited = element;
      }
    });

    const dialogRef = this._dialog.open(EditCategoryDialogComponent, {
      width: '250px',
      data: categoryToBeEdited
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });

  }

  delete(categoryName: string) {
    this._categoryService.deleteCategory(categoryName).subscribe(res => { }, err => {
      this._snackBar.open("Something went wrong,try again!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
        this.ngOnInit();
        this._snackBar.open("Category deleted!", 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
  }


}
