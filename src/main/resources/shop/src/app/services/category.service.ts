import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../app-constants';
import { Category } from '../models/category';
import { Observable } from 'rxjs';
import { text } from '@angular/core/src/render3';
import { Subcategory } from '../models/subcategory';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) { }

  getAllCategories(): Observable<Category[]>{
      return this._http.get<any>(AppConstants.baseUrl + "rest/categories");
  }

  addCategory(category:Category){
    return this._http.post(AppConstants.baseUrl + "rest/categories",category,{responseType:'text'});
  }

  // getAllSubcategories(): Observable<Subcategory[]>{
  //   dodaj kad napravi
  //   return this._http.get<any>(AppConstants.baseUrl + "rest/categories");
  //}
}
