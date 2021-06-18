import {Component, OnInit, ChangeDetectorRef, OnDestroy} from '@angular/core';
import {FormControl, FormBuilder, Validators, FormGroup} from '@angular/forms';
import {ProductService} from '../services/product.service';
import {CategoryService} from '../services/category.service';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {NgxDropzoneChangeEvent} from 'ngx-dropzone';
import {Subcategory} from '../data/subcategory';
import {Product} from '../data/product';
import {NewProduct} from '../data/new-product';

@Component({
  selector: 'app-admin-add-product',
  templateUrl: './admin-add-product.component.html',
  styleUrls: ['./admin-add-product.component.css']
})
export class AdminAddProductComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

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
      name: new FormControl('', [Validators.required]),
      nameSr: new FormControl('', [Validators.required]),
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
    super.ngOnInit();

    this._categories.getAllSubcategories().subscribe(res => {
      this.subcategories = res.map((name) => name);
      this.selectedSubcategory = this.subcategories[0].name;
    });
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public selectSubcategory(name: string): void {
    this.selectedSubcategory = name;
  }

  addProduct(addProductForm: FormGroup) {
    const product = new NewProduct(
      addProductForm.get('name')?.value,
      addProductForm.get('nameSr')?.value,
      addProductForm.get('code')?.value,
      addProductForm.get('description')?.value,
      addProductForm.get('descriptionSr')?.value,
      addProductForm.get('brand')?.value,
      this.selectedSubcategory,
      addProductForm.get('amountDin')?.value,
      addProductForm.get('amountEuro')?.value,
      addProductForm.get('quantity')?.value);

    this._productService.addProduct(product)
      .subscribe(res => {
        const {id: productId} = JSON.parse(res);
        if (this.files.length > 0) {
          this.files.map(file => {
            const formData: FormData = new FormData();
            formData.append('file', file, file.name);
            this._productService.addProductImages(productId, formData).subscribe(imgUpload => {
              this._snackBar.open(this.language.imageAdded, 'Success', {
                duration: 4000,
                panelClass: ['my-snack-bar']
              });
            }, err => {
              this._snackBar.open(this.language.errorSthWrong, 'Error', {
                duration: 4000,
                panelClass: ['my-snack-bar-error']
              });
            });
          });
        }
      }, err => {
        this._snackBar.open(this.language.errorSthWrong, 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      }, () => {
        this._snackBar.open(this.language.productAdded, 'Success', {
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
