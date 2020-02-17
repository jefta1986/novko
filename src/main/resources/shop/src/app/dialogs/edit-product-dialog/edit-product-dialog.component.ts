import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Product } from '../../models/product';

@Component({
  selector: 'app-edit-product-dialog',
  templateUrl: './edit-product-dialog.component.html',
  styleUrls: ['./edit-product-dialog.component.css']
})
export class EditProductDialogComponent implements OnInit {

  editProductForm: FormGroup;
  selectedImage;

  constructor(private _productService: ProductService,
    private _snackBar: MatSnackBar,
    private _dialogRef: MatDialogRef<EditProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public product: Product) {

      this.editProductForm = new FormGroup({
        name: new FormControl('', [Validators.required]),
        code: new FormControl('', [Validators.required]),
        description: new FormControl('', [Validators.required]),
        amountDin: new FormControl('', [Validators.required]),
        amountEuro: new FormControl('', [Validators.required]),
        quantity: new FormControl('', [Validators.required])
      });
      
  }

  ngOnInit() {
    this.selectedImage = this.product.defaultPicture;
    this.editProductForm.get('name').setValue(this.product.name);
    this.editProductForm.get('code').setValue(this.product.code);
    this.editProductForm.get('description').setValue(this.product.description);
    this.editProductForm.get('amountDin').setValue(this.product.amountDin);
    this.editProductForm.get('amountEuro').setValue(this.product.amountEuro);
    this.editProductForm.get('quantity').setValue(this.product.quantity);
  }

  changeDefaultPicture(selectedDefaultPicture){
    this.product.defaultPicture = selectedDefaultPicture;
  }

  editProduct(){
    this.product.name = this.editProductForm.get('name').value;
    this.product.code = this.editProductForm.get('code').value;
    this.product.description = this.editProductForm.get('description').value;
    this.product.amountDin = this.editProductForm.get('amountDin').value;
    this.product.amountEuro = this.editProductForm.get('amountEuro').value;
    this.product.quantity = this.editProductForm.get('quantity').value;
    this._productService.editProduct(this.product).subscribe(res=>{}, err => {
      this._snackBar.open("Something went wrong,try again!", 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open("Product edited!", 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this._dialogRef.close();
    });
  }

}
