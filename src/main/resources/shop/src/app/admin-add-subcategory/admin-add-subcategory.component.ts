import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Subcategory} from 'src/app/data/subcategory';
import {FormGroup, Validators, FormControl} from '@angular/forms';
import {CategoryService} from 'src/app/services/category.service';
import {Category} from 'src/app/data/category';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CategoriesModel} from '../data/models/categories.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';

@Component({
  selector: 'app-admin-add-subcategory',
  templateUrl: './admin-add-subcategory.component.html',
  styleUrls: ['./admin-add-subcategory.component.css']
})
export class AdminAddSubcategory extends CommonAbstractComponent implements OnInit {
  public get categories(): Category[] {
    return this._categoriesModel.categories;
  }

  public subcategoryForm: FormGroup;

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.subcategories, '/admin-categories/admin-subcategory'),
  ];

  constructor(private _categoriesModel: CategoriesModel,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    this.subcategoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      nameSr: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
    this._categoriesModel.loadSubcategories();
  }

  addSubcategory() {
    const subcategory = new Subcategory(this.subcategoryForm.get('name')?.value, this.subcategoryForm.get('nameSr')?.value);
    this._categoriesModel.addSubcategory(subcategory, this.subcategoryForm.get('category')?.value);
  }
}
