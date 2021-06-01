import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {AddCategoryDialogComponent} from '../dialogs/addcategorydialog/add-category-dialog.component';
import {EditCategoryDialogComponent} from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Category} from '../models/category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public allCategories: Category[] = [];

  constructor(private _categoryService: CategoryService,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(res => {
      this.allCategories = res;
    });
  }

  openDialog(): void {
    const dialogRef = this._dialog.open(AddCategoryDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  edit(categoryName: string) {
    let categoryToBeEdited = {};
    this.allCategories.forEach((element: Category) => {
      if (element.name === categoryName) {
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
    this._categoryService.deleteCategory(categoryName).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this.ngOnInit();
      this._snackBar.open('Category deleted!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }
}
