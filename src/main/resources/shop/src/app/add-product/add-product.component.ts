import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Product } from '../models/product';
import { ProductService } from '../services/product.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  private addProductForm: FormGroup;
  private fileImage = [];

  constructor(private formBuilder: FormBuilder, private _productService: ProductService,private _snackBar: MatSnackBar) {
    this.addProductForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      code: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      amountDin: new FormControl('', [Validators.required]),
      amountEuro: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit() {
  }

  addProduct(addProductForm: FormGroup) {
    var product = new Product();
    product.name = addProductForm.get('name').value;
    product.code = addProductForm.get('code').value;
    product.description = addProductForm.get('description').value;
    product.amountDin = addProductForm.get('amountDin').value;
    product.amountEuro = addProductForm.get('amountEuro').value;
    product.quantity = addProductForm.get('quantity').value;

    let productJson = JSON.stringify(product);

    let formData: FormData = new FormData();
    this.fileImage.forEach(element => {
      console.log(element[0]);
      formData.append('file', element[0]);
    });

    this._productService.addProductWithImages(productJson, formData).subscribe(res=>{},err=>{
      this._snackBar.open("Something went wrong,try again!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    },()=>{
      this._snackBar.open("Product added!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  fileChange(event) {
    this.fileImage.push(event.target.files);
  }

}
