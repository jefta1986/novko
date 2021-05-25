import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {FormControl, FormBuilder, Validators, FormGroup} from '@angular/forms';
import {Product, ProductBackend} from '../models/product';
import {ProductService} from '../services/product.service';
import {MatSnackBar} from '@angular/material';
import {CategoryService} from '../services/category.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  addProductForm: FormGroup;
  private fileImage = [];
  private subcategories = [];
  selectedSubcategory;

  @ViewChild('fileInput1') el1: ElementRef;
  @ViewChild('fileInput2') el2: ElementRef;
  @ViewChild('fileInput3') el3: ElementRef;
  @ViewChild('fileInput4') el4: ElementRef;
  @ViewChild('fileInput5') el5: ElementRef;

  constructor(private _categories: CategoryService, private formBuilder: FormBuilder, private _productService: ProductService, private _snackBar: MatSnackBar) {
    this.addProductForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      code: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      amountDin: new FormControl('', [Validators.required]),
      amountEuro: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this._categories.getAllSubcategories().subscribe(res => {
      this.subcategories = res;
      this.selectedSubcategory = this.subcategories[0].name;
    });
  }

  addProduct(addProductForm: FormGroup) {
    this.fileUpload();
    const product = new ProductBackend();
    product.name = addProductForm.get('name').value;
    product.code = addProductForm.get('code').value;
    product.description = addProductForm.get('description').value;
    product.amountDin = addProductForm.get('amountDin').value;
    product.amountEuro = addProductForm.get('amountEuro').value;
    product.quantity = addProductForm.get('quantity').value;

    const productJson = JSON.stringify(product);

    const formData: FormData = new FormData();
    this.fileImage.forEach(element => {
      formData.append('file', element);
    });

    this._productService.addProductWithImages(productJson, formData).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._productService.addProductToSubcategory(addProductForm.get('name').value, this.selectedSubcategory)
        .subscribe(res => {
        }, err => {
          this._snackBar.open('Something went wrong,try again!', 'Error', {
            duration: 4000,
            panelClass: ['my-snack-bar-error']
          });
        }, () => {
          this._snackBar.open('Product added!', 'Success', {
            duration: 4000,
            panelClass: ['my-snack-bar']
          });
        });
    });
  }

  fileUpload() {
    if (this.el1.nativeElement.files[0] !== undefined) {
      this.fileImage.push(this.el1.nativeElement.files[0]);
    }
    if (this.el2.nativeElement.files[0] !== undefined) {
      this.fileImage.push(this.el2.nativeElement.files[0]);
    }
    if (this.el3.nativeElement.files[0] !== undefined) {
      this.fileImage.push(this.el3.nativeElement.files[0]);
    }
    if (this.el4.nativeElement.files[0] !== undefined) {
      this.fileImage.push(this.el4.nativeElement.files[0]);
    }
    if (this.el5.nativeElement.files[0] !== undefined) {
      this.fileImage.push(this.el5.nativeElement.files[0]);
    }
  }

}
