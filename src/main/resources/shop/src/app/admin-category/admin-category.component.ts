import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {EditCategoryDialogComponent} from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Category} from '../data/category';
import {CategoriesModel} from '../data/models/categories.model';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {AdditionalLinks} from '../data/additional-links';
import {NoItem} from '../common/common-language.interface';
import {ConfirmDialogComponent} from '../dialogs/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styleUrls: ['./admin-category.component.css']
})
export class AdminCategoryComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get categories(): Category[] {
    return this._categoriesModel.categories;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addCategory, '/admin-categories/add-category'),
    new AdditionalLinks(this.language.subcategories, '/admin-categories/admin-subcategory'),
  ];

  public noItemType: NoItem = NoItem.categories;

  constructor(private _categoriesModel: CategoriesModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    this._categoriesModel.loadCategoriesSubcategories();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public edit(category: Category) {
    this._dialog.open(EditCategoryDialogComponent, {
      width: '250px',
      data: category
    });
  }

  public delete(category: Category) {
    const dialogRef = this._dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this._categoriesModel.deleteCategory(category);
      }
    });
  }
}
