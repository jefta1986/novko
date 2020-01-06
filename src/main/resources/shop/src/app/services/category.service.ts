import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../app-constants';
import { Category } from '../models/category';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) { }

  getAllCategories(): Observable<Category[]>{
      return this._http.get<any>(AppConstants.baseUrl + "rest/categories");
   }

}
