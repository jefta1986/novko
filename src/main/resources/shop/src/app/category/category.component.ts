import { Component, OnInit } from '@angular/core';
import { Category } from '../models/category';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { CategoryService } from '../services/category.service';
import { MatDialog } from '@angular/material';
import { AddcategorydialogComponent } from '../dialogs/addcategorydialog/addcategorydialog.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  private allCategories = [];

  constructor(private _categoryService:CategoryService,private _dialog: MatDialog) {
  
  }

  openDialog(): void {
    const dialogRef = this._dialog.open(AddcategorydialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(res=>{
        this.allCategories = res;
    });
  }


}
