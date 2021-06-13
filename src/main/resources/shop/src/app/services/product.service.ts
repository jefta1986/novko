import {Injectable} from '@angular/core';
import {Product} from '../data/product';
import {AppConstants} from '../app-constants';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SubcategoryProducts} from '../data/subcategoryProduct';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private _http: HttpClient) {
  }

  addProduct(product: any) {
    return this._http.post(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  editProduct(product: Product) {
    return this._http.put(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  deleteProductByCode(code: string) {
    return this._http.delete(AppConstants.baseUrl + `rest/products?code=${code}`);
  }

  getAllProductsWithImages(): Observable<Product[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/products');
  }

  addProductImages(productId: number, formData: FormData) {
    const params = new HttpParams();
    params.append('productId', productId.toString());

    const options = {
      params: params,
      reportProgress: true,
    };

    const req = new HttpRequest('POST', `${AppConstants.baseUrl}rest/products/upload?productId=${productId}`, formData, options);

    return this._http.request(req);
  }

  getProductByCode(code: string): any {
    return this._http.get(AppConstants.baseUrl + `rest/products/${code}`);
  }

  getProductsFromSubcategories(subcategoryName: string): Observable<Product[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/categories/products?subcategoryName=${subcategoryName}`);
  }
}
