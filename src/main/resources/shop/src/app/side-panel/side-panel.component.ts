import { Component, OnInit, Input } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-panel',
  templateUrl: './side-panel.component.html',
  styleUrls: ['./side-panel.component.css']
})
export class SidePanelComponent implements OnInit {


  @Input() categories :Category[];

  constructor(private _categoryService:CategoryService,private _router:Router) { }

  ngOnInit() {
    this._categoryService.getAllCategories().subscribe(
      res=>{
        this.categories = res;
      }
    );
  }

}
