import {Component, OnInit, ChangeDetectorRef, OnDestroy, ViewChild} from '@angular/core';
import {FormControl, FormBuilder, Validators, FormGroup} from '@angular/forms';
import {ProductService} from '../services/product.service';
import {CategoryService} from '../services/category.service';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {NgxDropzoneChangeEvent, NgxDropzoneComponent} from 'ngx-dropzone';
import {Subcategory} from '../data/subcategory';
import {ProductModel} from '../data/models/product.model';
import {EditProduct} from '../data/edit-product';
import {ActivatedRoute, Router} from '@angular/router';
import {Product} from '../data/product';
import {AdditionalLinks} from '../data/additional-links';

export const getBase64FromUrl = async (url: string) => {
  const data = await fetch(url);
  const blob = await data.blob();
  const buffer = await blob.arrayBuffer();
  return new Promise((resolve) => {
    const reader = new FileReader();
    reader.readAsDataURL(blob);
    reader.onloadend = () => {
      const base64data = reader.result;
      resolve({base64: base64data, blob: blob, data: data, buffer: buffer});
    };
  });
};

@Component({
  selector: 'app-admin-product-code',
  templateUrl: './admin-product-code.component.html',
  styleUrls: ['./admin-product-code.component.css']
})
export class AdminProductCodeComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  @ViewChild('nationalDropZone') componentRef?: NgxDropzoneComponent;
  public additionalLinks: AdditionalLinks[] = [];
  public editProductForm: FormGroup;
  public subcategories: Subcategory[] = [];
  public selectedSubcategory = '';
  public files: File[] = [];

  constructor(private _categories: CategoryService,
              private formBuilder: FormBuilder,
              private _productService: ProductService,
              private _productModel: ProductModel,
              private _snackBar: MatSnackBar,
              protected commonLanguageModel: CommonLanguageModel,
              protected cdr: ChangeDetectorRef,
              private _activatedRoute: ActivatedRoute,
              private _router: Router) {
    super(cdr, commonLanguageModel);

    this.editProductForm = new FormGroup({
      name: new FormControl(this._productModel.product?.name || '', [Validators.required]),
      nameSr: new FormControl(this._productModel.product?.nameSr || '', [Validators.required]),
      code: new FormControl(this._productModel.product?.code || '', [Validators.required]),
      description: new FormControl(this._productModel.product?.description || '', [Validators.required]),
      descriptionSr: new FormControl(this._productModel.product?.descriptionSr || '', [Validators.required]),
      brand: new FormControl(this._productModel.product?.brand || '', [Validators.required]),
      amountDin: new FormControl(this._productModel.product?.amountDin || '', [Validators.required]),
      amountEuro: new FormControl(this._productModel.product?.amountEuro || '', [Validators.required]),
      quantity: new FormControl(this._productModel.product?.quantity || '', [Validators.required])
    });
  }

  ngOnInit(): void {
    super.ngOnInit();
    const code = this._activatedRoute.snapshot.paramMap.get('code');
    if (code !== null) {
      this._productModel.loadProductByCode(code).subscribe((product) => {
        this.initProduct(product);
        this.initFormData(product);

        this.additionalLinks = [new AdditionalLinks(this.language.viewProduct, `/product/${product.code}`)];
      });
      this._categories.getAllSubcategories().subscribe(res => {
        this.subcategories = res.map((name) => name);
        if (this._productModel.product) {
          this.initCategory(this._productModel.product);
        }
      });
    } else {
      this._router.navigate(['/home']);
    }
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  private initFormData(product: Product): void {
    this.editProductForm.get('name')?.setValue(product.name);
    this.editProductForm.get('nameSr')?.setValue(product.nameSr);
    this.editProductForm.get('code')?.setValue(product.code);
    this.editProductForm.get('description')?.setValue(product.description);
    this.editProductForm.get('descriptionSr')?.setValue(product.descriptionSr);
    this.editProductForm.get('brand')?.setValue(product.brand);
    this.editProductForm.get('amountDin')?.setValue(product.amountDin);
    this.editProductForm.get('amountEuro')?.setValue(product.amountEuro);
    this.editProductForm.get('quantity')?.setValue(product.quantity);
    this.initCategory(product);
  }

  private initCategory(product: Product): void {
    const subcategory = this.subcategories.find(({name}) => name === product.subcategory.name);
    this.selectedSubcategory = subcategory?.name || this.subcategories[0].name;
  }

  private initProduct(product: Product): void {
    if (product.images.length > 0) {
      const files: File[] = [];
      product.images.forEach(image => {
        getBase64FromUrl(`${image}`)
          .then((data: any) => {
              files.push({
                arrayBuffer(): Promise<ArrayBuffer> {
                  return Promise.resolve(data.buffer);
                },
                lastModified: Date.now(),
                slice(start: number | undefined, end: number | undefined, contentType: string | undefined): Blob {
                  return data.blob;
                },
                stream(): ReadableStream {
                  return data.data;
                },
                text(): Promise<string> {
                  return data.data.text();
                },
                name: image,
                size: 12345,
                type: `image/${image.split('.').pop()}`
              });
            }
          );
      });
      this.files = files;
    }
  }

  public selectSubcategory(name: string): void {
    this.selectedSubcategory = name;
  }

  public editProduct(editProductForm: FormGroup): void {
    const product = new EditProduct(
      this._productModel.product.id,
      editProductForm.get('name')?.value,
      editProductForm.get('nameSr')?.value,
      editProductForm.get('code')?.value,
      true,
      editProductForm.get('description')?.value,
      editProductForm.get('descriptionSr')?.value,
      editProductForm.get('brand')?.value,
      this.selectedSubcategory,
      editProductForm.get('amountDin')?.value,
      editProductForm.get('amountEuro')?.value,
      editProductForm.get('quantity')?.value
    );

    this._productService.editProduct(product)
      .subscribe(res => {
        const {id: productId} = JSON.parse(res);
        if (this.files.length > 0) {
          this.files.filter(({name}) => !name.includes('http')).map(file => {
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

  public onSelect(event: NgxDropzoneChangeEvent): void {
    if (this.files.length + event.addedFiles.length < 5) {
      this.files.push(...event.addedFiles);
    } else {
      this._snackBar.open(this.language.errorUploadMultiple, 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }
  }

  public onRemove(event: File): void {
    if (event.name.includes('http')) {
      this._productService.deleteProductImages(this._productModel.product.id, event.name)
        .subscribe(() => {
          this.files.splice(this.files.indexOf(event), 1);
        });
    } else {
      this.files.splice(this.files.indexOf(event), 1);
    }
  }

}
