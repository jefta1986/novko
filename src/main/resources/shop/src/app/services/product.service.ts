import {Injectable} from '@angular/core';
import {Product} from '../models/product';
import {AppConstants} from '../app-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SubcategoryProducts} from '../models/subcategoryProduct';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private _http: HttpClient) {
  }

  addProduct(product: Product) {
    return this._http.post(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  editProduct(product: Product) {
    return this._http.put(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  deleteProduct(productName: String) {
    return this._http.delete(AppConstants.baseUrl + 'rest/products?productName=' + productName, {responseType: 'text'});
  }

  getAllProductsWithoutImages(): Observable<Product[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/products');
  }

  getAllProductsWithImages(): Observable<Product[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/products');
  }

  addProductWithImages(productJson: string, formData: FormData) {
    return this._http.post(AppConstants.baseUrl + 'rest/products/savewithimage?product=' + encodeURIComponent(productJson)
      , formData, {responseType: 'text'});
  }

  getProductByCode(code: string): any {
    return this._http.get(AppConstants.baseUrl + `rest/products/${code}`);
  }

  addProductToSubcategory(productName: string, subcategoryName: string) {
    return this._http.post(AppConstants.baseUrl
      + 'rest/products/add?productName=' + productName + '&subcategoryName=' + subcategoryName
      , null, {responseType: 'text'});
  }

  getProductsFromSubcategories(subcategoryName: string): Observable<SubcategoryProducts> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/categories/getSubcategory?name=' + subcategoryName);
  }
}
