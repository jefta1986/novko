import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { AppConstants } from '../app-constants';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private _http:HttpClient) { }

  addProduct(product:Product){
    return this._http.post(AppConstants.baseUrl + 'rest/products/',product,{responseType:'text'});
  }

  addProductWithImages(productJson:string, formData:FormData){
    return this._http.post(AppConstants.baseUrl + 'rest/products/savewithimage?product=' + encodeURIComponent(productJson) 
                          ,formData,{responseType:'text'});
  }

  getProductByName(productName:string){
    return this._http.get(AppConstants.baseUrl + 'rest/products/getName?productName=' + productName);
  }

}
