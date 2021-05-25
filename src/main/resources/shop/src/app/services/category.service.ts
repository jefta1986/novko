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

  editCategory(category:Category){
    return this._http.put(AppConstants.baseUrl + "rest/categories",category,{responseType:'text'});
  }

  deleteCategory(categoryName:string){
    return this._http.delete(AppConstants.baseUrl + "rest/categories?categoryName=" + categoryName,{responseType:'text'});
  }

  getAllSubcategories(): Observable<Subcategory[]>{
    return this._http.get<any>(AppConstants.baseUrl + "rest/categories/subcategories/all");
  }

  addSubcategory(subcategory:Subcategory,categoryName:string){
    return this._http.post(AppConstants.baseUrl + "rest/categories/addSubcategory?categoryName=" + categoryName,
                            subcategory,{responseType:'text'});
  }

  editSubcategory(subcategoryOld:Subcategory,categoryName:string,subcategoryNewName:string){
    return this._http.put(AppConstants.baseUrl +
                        "rest/categories/updateSubcategory?subcategoryName="+ subcategoryNewName+
                        '&categoryName=' + categoryName
                        ,subcategoryOld,{responseType:'text'});
  }


  deleteSubcategory(categoryName:string,subcategoryName:String){
    return this._http.delete(AppConstants.baseUrl + "rest/categories/deleteSubcategory?categoryName="
                                  + categoryName,{responseType:'text'});
  }

}
