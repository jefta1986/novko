import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {FormControl, FormBuilder, Validators, FormGroup} from '@angular/forms';
import {ProductService} from '../services/product.service';
import {CategoryService} from '../services/category.service';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {NgxDropzoneChangeEvent} from 'ngx-dropzone';
import {Subcategory} from '../models/subcategory';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent extends CommonAbstractComponent implements OnInit {

  public addProductForm: FormGroup;
  public subcategories: Subcategory[] = [];
  public selectedSubcategory = '';
  public files: File[] = [];

  constructor(private _categories: CategoryService,
              private formBuilder: FormBuilder,
              private _productService: ProductService,
              private _snackBar: MatSnackBar,
              protected commonLanguageModel: CommonLanguageModel,
              protected cdr: ChangeDetectorRef) {
    super(cdr, commonLanguageModel);

    this.addProductForm = new FormGroup({
      productName: new FormControl('', [Validators.required]),
      code: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      descriptionSr: new FormControl('', [Validators.required]),
      brand: new FormControl('', [Validators.required]),
      amountDin: new FormControl('', [Validators.required]),
      amountEuro: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {

    this._categories.getAllSubcategories().subscribe(res => {
      this.subcategories = res.map((name) => name);
      this.selectedSubcategory = this.subcategories[0].name;
    });
  }

  public selectSubcategory(name: string): void {
    this.selectedSubcategory = name;
  }

  addProduct(addProductForm: FormGroup) {
    const product = {
      amountDin: addProductForm.get('amountDin')?.value,
      amountEuro: addProductForm.get('amountEuro')?.value,
      brand: addProductForm.get('brand')?.value,
      code: addProductForm.get('code')?.value,
      description: addProductForm.get('description')?.value,
      descriptionSr: addProductForm.get('descriptionSr')?.value,
      name: addProductForm.get('productName')?.value,
      quantity: addProductForm.get('quantity')?.value,
      subcategoryName: this.selectedSubcategory,
    };

    this._productService.addProduct(product)
      .subscribe(res => {
        const {id: productId} = JSON.parse(res);
        if (this.files.length > 0) {
          this.files.map(file => {
            const formData: FormData = new FormData();
            formData.append('file', file, file.name);
            this._productService.addProductImages(productId, formData).subscribe(imgUpload => console.log(imgUpload));
          });
        }
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
  }

  onSelect(event: NgxDropzoneChangeEvent) {
    if (this.files.length + event.addedFiles.length < 5) {
      this.files.push(...event.addedFiles);
    } else {
      this._snackBar.open(this.language.errorUploadMultiple, 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }
  }

  onRemove(event: File) {
    this.files.splice(this.files.indexOf(event), 1);
  }

}
