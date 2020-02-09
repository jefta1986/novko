import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { AddSubcategoryDialogComponent } from '../dialogs/add-subcategory-dialog/add-subcategory-dialog.component';
import { Subcategory } from '../models/subcategory';
import { EditSubcategoryDialogComponent } from '../dialogs/edit-subcategory-dialog/edit-subcategory-dialog.component';

@Component({
  selector: 'app-subcategory',
  templateUrl: './subcategory.component.html',
  styleUrls: ['./subcategory.component.css']
})
export class SubcategoryComponent implements OnInit {

  private allSubcategories;
  categoryName = '';

  constructor(private _categoryService: CategoryService
              , private _dialog: MatDialog
              , private _snackBar: MatSnackBar) {

  }

  openDialog(): void {
    const dialogRef = this._dialog.open(AddSubcategoryDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  ngOnInit() {
    this._categoryService.getAllSubcategories().subscribe(res => {
      this.allSubcategories = res;
    });
  }

  delete(subcategoryName){
    this.getCategoryNameForSubcategoryDelete(subcategoryName);
  }

  getCategoryNameForSubcategoryDelete(subcategoryName: string) {
    var categories = [];
    this._categoryService.getAllCategories().subscribe(res => {
      categories = res;
    }, err => { }, () => {
      categories.forEach(category => {
        category.subcategories.forEach(subcategory => {
          if(subcategory.name == subcategoryName){
            this.categoryName = category.name;
          }
        });
      });
      this._categoryService.deleteSubcategory(this.categoryName,subcategoryName).subscribe(res=>{},err=>{
        this._snackBar.open("Something went wrong,try again!", 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      },()=>{
        this.ngOnInit();
        this._snackBar.open("Category deleted!", 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
      });
    });
  }

  edit(subcategory:Subcategory){
    this.getCategoryNameForSubcategoryUpdate(subcategory);
  }

  getCategoryNameForSubcategoryUpdate(subcategory:Subcategory) {
    var categories = [];
    this._categoryService.getAllCategories().subscribe(res => {
      categories = res;
    }, err => { }, () => {
      categories.forEach(category => {
        category.subcategories.forEach(subcategory => {
          if(subcategory.name == subcategory.name){
            this.categoryName = category.name;
          }
        });
      });
      //update dialog
      const dialogRef = this._dialog.open(EditSubcategoryDialogComponent, {
        width: '250px',
        data: {
          categoryName: this.categoryName,
          subcategory:subcategory
        }
      });
  
      dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
      });
    });
  }

}
