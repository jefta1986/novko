import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {AdminAddCategory} from '../admin-add-category/admin-add-category.component';
import {EditCategoryDialogComponent} from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Category} from '../data/category';
import {CategoriesModel} from '../data/models/categories.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';

@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styleUrls: ['./admin-category.component.css']
})
export class AdminCategoryComponent extends CommonAbstractComponent implements OnInit {

  public get allCategories(): Category[] {
    return this._categoriesModel.categories;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addCategory, '/admin-categories/add-category'),
    new AdditionalLinks(this.language.subcategories, '/admin-categories/admin-subcategory'),
  ];

  constructor(private _categoriesModel: CategoriesModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    this._categoriesModel.loadCategories();
  }

  public edit(category: Category) {
    this._dialog.open(EditCategoryDialogComponent, {
      width: '250px',
      data: category
    });
  }

  public delete(category: Category) {
    this._categoriesModel.deleteCategory(category);
  }
}
