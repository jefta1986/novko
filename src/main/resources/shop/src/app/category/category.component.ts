import { Component, OnInit } from '@angular/core';
import { Category } from '../models/category';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private _categoryService:CategoryService) {
  
  }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(res=>{console.log(res)});
  }

}
