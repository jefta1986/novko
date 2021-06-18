import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Subcategory, SubcategoryEdit} from '../data/subcategory';
import {EditSubcategoryDialogComponent} from '../dialogs/edit-subcategory-dialog/edit-subcategory-dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {CategoriesModel} from '../data/models/categories.model';
import {AdditionalLinks} from '../data/additional-links';
import {NoItem} from '../common/common-language.interface';
import {ConfirmDialogComponent} from '../dialogs/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-admin-subcategory',
  templateUrl: './admin-subcategory.component.html',
  styleUrls: ['./admin-subcategory.component.css']
})
export class AdminSubcategoryComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public get subcategories(): Subcategory[] {
    return this._categoriesModel.subCategories;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addSubcategory, '/admin-categories/add-subcategory'),
    new AdditionalLinks(this.language.categories, '/admin-categories'),
  ];

  public noItemType: NoItem = NoItem.subcategories;

  constructor(private _categoriesModel: CategoriesModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    super.ngOnInit();
    this._categoriesModel.loadAdminSubcategories();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public delete(subcategory: Subcategory): void {
    const dialogRef = this._dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        const category = this._categoriesModel.categories.find(category => {
          return category.subcategories.find(categorySub => categorySub.id === subcategory.id);
        });
        if (category) {
          this._categoriesModel.deleteSubcategory(subcategory, category);
        }
      }
    });
  }

  public edit(subcategory: Subcategory): void {
    const category = this._categoriesModel.categories.find(category => {
      return category.subcategories.find(categorySub => categorySub.id === subcategory.id)
    });
    if (category) {
      this._dialog.open(EditSubcategoryDialogComponent, {
        width: '250px',
        data: new SubcategoryEdit(category, subcategory)
      });
    }
  }

}
