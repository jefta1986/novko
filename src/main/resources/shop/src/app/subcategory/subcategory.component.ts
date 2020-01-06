import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { MatDialog } from '@angular/material';
import { AddSubcategoryDialogComponent } from '../dialogs/add-subcategory-dialog/add-subcategory-dialog.component';

@Component({
  selector: 'app-subcategory',
  templateUrl: './subcategory.component.html',
  styleUrls: ['./subcategory.component.css']
})
export class SubcategoryComponent implements OnInit {

  private allSubcategories = [];

  constructor(private _categoryService:CategoryService,private _dialog: MatDialog) {
  
  }

  openDialog(): void {
    const dialogRef = this._dialog.open(AddSubcategoryDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
    // this._categoryService.getAllCategories().subscribe(res=>{
    //     this.allSubcategories = res;
    // });
  }

}
