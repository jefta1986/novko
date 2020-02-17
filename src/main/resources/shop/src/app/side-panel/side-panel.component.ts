import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-panel',
  templateUrl: './side-panel.component.html',
  styleUrls: ['./side-panel.component.css']
})
export class SidePanelComponent implements OnInit {


  @Input('categories') categories;
  @Output() routeChange = new EventEmitter();
  
  constructor(private _categoryService:CategoryService,private _router:Router) { }

  onRouteChanged(){
    this.routeChange.emit(true);
  }

  ngOnInit() {
    // this._categoryService.getAllCategories().subscribe(
    //   res=>{
    //     this.categories = res;
    //   }
    // );
  }

}
