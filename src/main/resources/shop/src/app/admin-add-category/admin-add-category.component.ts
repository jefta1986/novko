import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Category} from 'src/app/data/category';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {CategoryService} from 'src/app/services/category.service';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CategoriesModel} from '../data/models/categories.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';

@Component({
  selector: 'app-admin-add-category',
  templateUrl: './admin-add-category.component.html',
  styleUrls: ['./admin-add-category.component.css']
})
export class AdminAddCategory extends CommonAbstractComponent implements OnInit {
  public categoryForm: FormGroup;

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.categories, '/admin-categories'),
  ];

  constructor(private _categoriesModel: CategoriesModel,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel:  CommonLanguageModel) {
    super(cdr, commonLanguageModel);
    this.categoryForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      nameSr: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  addCategory() {
    const category = new Category(this.categoryForm.get('name')?.value, this.categoryForm.get('nameSr')?.value, 5, []);
    this._categoriesModel.addCategory(category);
  }

}
