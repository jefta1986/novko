import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AdminAddSubcategory} from '../admin-add-subcategory/admin-add-subcategory.component';
import {Subcategory, SubcategoryEdit} from '../data/subcategory';
import {EditSubcategoryDialogComponent} from '../dialogs/edit-subcategory-dialog/edit-subcategory-dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {CategoriesModel} from '../data/models/categories.model';
import {AdditionalLinks} from '../data/additional-links';

@Component({
  selector: 'app-admin-subcategory',
  templateUrl: './admin-subcategory.component.html',
  styleUrls: ['./admin-subcategory.component.css']
})
export class AdminSubcategoryComponent extends CommonAbstractComponent implements OnInit {

  public get allSubcategories(): Subcategory[] {
    return this._categoriesModel.subCategories;
  }

  public additionalLinks: AdditionalLinks[] = [
    new AdditionalLinks(this.language.addSubcategory, '/admin-categories/add-subcategory'),
    new AdditionalLinks(this.language.categories, '/admin-categories'),
  ];

  constructor(private _categoriesModel: CategoriesModel,
              private _dialog: MatDialog,
              private _snackBar: MatSnackBar,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit() {
    this._categoriesModel.loadSubcategories();
  }

  delete(subcategory: Subcategory) {
    this._categoriesModel.deleteSubcategory(subcategory);
  }

  edit(subcategory: Subcategory) {
    this._dialog.open(EditSubcategoryDialogComponent, {
      width: '250px',
      data: new SubcategoryEdit(subcategory.name, subcategory)
    });
  }

}
