import {Injectable} from '@angular/core';
import {Product} from '../data/product';
import {AppConstants} from '../app-constants';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SubcategoryProducts} from '../data/subcategoryProduct';
import {EditProduct} from '../data/edit-product';
import {ProductsSort} from '../data/products.sort';
import {Pagination} from '../data/pagination';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private _http: HttpClient) {
  }

  addProduct(product: any) {
    return this._http.post(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  editProduct(product: EditProduct) {
    return this._http.put(AppConstants.baseUrl + 'rest/products/', product, {responseType: 'text'});
  }

  deleteProductByCode(code: string) {
    return this._http.delete(AppConstants.baseUrl + `rest/products?code=${code}`);
  }

  getAllProductsWithImages(): Observable<Product[]> {
    return this._http.get<any>(AppConstants.baseUrl + 'rest/products');
  }

  getAllProductsWithImagesPaginated(productsSort: ProductsSort, isAdmin: boolean, searchTextParams: { name: string, nameSr: string, codePart: string }): Observable<Pagination> {
    const {
      page,
      size,
      sort,
      direction,
    } = productsSort;
    return this._http.get<any>(`${AppConstants.baseUrl}rest/products/filtered?page=${page}${searchTextParams.codePart === '' ? '' : '&codePart=' + searchTextParams.codePart}${searchTextParams.name === '' ? '' : ('&namePart=' + searchTextParams.name)}${searchTextParams.nameSr === '' ? '' : ('&namePartSr=' + searchTextParams.nameSr)}${!isAdmin ? '&active=true' : ''}&size=${size}&sort=${sort}&direction=${direction}`);
  };

  getAllProductsSubcategoryWithImagesPaginated(productsSort: ProductsSort, subcategory: string, isAdmin: boolean, searchTextParams: { name: string, nameSr: string }): Observable<Pagination> {
    const {
      page,
      size,
      sort,
      direction,
    } = productsSort;
    return this._http.get<any>(`${AppConstants.baseUrl}rest/products/subcategory/filtered?page=${page}${searchTextParams.name === '' ? '' : ('&namePart=' + searchTextParams.name)}${searchTextParams.nameSr === '' ? '' : ('&namePartSr=' + searchTextParams.nameSr)}${!isAdmin ? '&active=true' : ''}&size=${size}&sort=${sort}&direction=${direction}&subcategoryName=${subcategory}`);
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

  deleteProductImages(productId: number, fileName: string) {
    return this._http.delete(`${AppConstants.baseUrl}rest/products/upload/delete?fileName=${fileName}&productId=${productId}`);
  }

  getProductByCode(code: string): any {
    return this._http.get(AppConstants.baseUrl + `rest/products/${code}`);
  }

  getProductsFromSubcategories(subcategoryName: string): Observable<Product[]> {
    return this._http.get<any>(`${AppConstants.baseUrl}rest/categories/products?subcategoryName=${subcategoryName}`);
  }

  order(params: any, username: string | undefined): Observable<any> {
    return this._http.post<any>(`${AppConstants.baseUrl}rest/orders?username=${username}`, params);
  }
}
