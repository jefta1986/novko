import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConstants} from '../app-constants';
import {Category} from '../data/category';
import {Observable} from 'rxjs';
import {Subcategory} from '../data/subcategory';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http: HttpClient) {
  }

  getAllCategories(): Observable<Category[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/categories');
  }

  getAllCategoriesSubcategories(): Observable<Category[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/categories/categories-subcategories');
  }

  addCategory(category: Category) {
    return this._http.post(`${AppConstants.baseUrl}rest/categories?name=${category.name}&nameSr=${category.nameSr}`, {});
  }

  editCategory(category: Category) {
    return this._http.patch(`${AppConstants.baseUrl}rest/categories`, category);
  }

  deleteCategory(categoryName: string) {
    return this._http.delete(AppConstants.baseUrl + 'rest/categories?categoryName=' + categoryName);
  }

  getAllSubcategories(): Observable<Subcategory[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/categories/subcategories/all');
  }

  addSubcategory(subcategory: Subcategory, categoryName: string) {
    return this._http.post(`${AppConstants.baseUrl}rest/categories/subcategories?categoryName=${categoryName}&subcategoryName=${subcategory.name}&subcategoryNameSr=${subcategory.nameSr}`, subcategory);
  }

  editSubcategory(subcategory: Subcategory, categoryName: string) {
    return this._http.patch(`${AppConstants.baseUrl}rest/categories/subcategories?subcategoryName=${subcategory.name}&subcategoryNameSr=${subcategory.nameSr}&categoryName=${categoryName}`, subcategory);
  }

  deleteSubcategory(subcategory: Subcategory, category: Category) {
    return this._http.delete(`${AppConstants.baseUrl}rest/categories/subcategories?categoryName=${category.name}&subcategoryName=${subcategory.name}`);
  }

}
