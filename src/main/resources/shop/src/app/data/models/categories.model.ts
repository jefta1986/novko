import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Category} from '../category';
import {CategoryService} from '../../services/category.service';
import {Subcategory} from '../subcategory';
import {AppConstants} from '../../app-constants';
import {CommonLanguageModel} from '../../common/common-language.model';

@Injectable()
export class CategoriesModel {
  private _categories: Category[] = [];
  private _category: Category = this._categories[0];

  private _subCategories: Subcategory[] = [];
  private _subCategory: Subcategory | null = null;

  private errorLoading = false;

  public get category(): Category {
    return this._category;
  }

  public get categories(): Category[] {
    return this._categories;
  }

  public get subCategory(): Subcategory | null {
    return this._subCategory;
  }

  public get subCategories(): Subcategory[] {
    return this._subCategories;
  }

  constructor(private categoryService: CategoryService,
              private commonLanguageModel: CommonLanguageModel,
              private _snackBar: MatSnackBar) {
  }

  public loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (result) => {
        this._categories = result.map(({
                                         id,
                                         name,
                                         nameSr,
                                       }) => new Category(
          name,
          nameSr,
          id,
        ));

      },
      (err) => this.errorLoading = true);
  }

  public loadCategoriesSubcategories(): void {
    this.categoryService.getAllCategoriesSubcategories().subscribe(
      (result) => {
        this._categories = result.map(({
                                         id,
                                         name,
                                         nameSr,
                                         subcategories
                                       }) => new Category(
          name,
          nameSr,
          id,
          subcategories
        ));

      },
      (err) => this.errorLoading = true);
  }

  public loadAdminSubcategories(): void {
    this.categoryService.getAllCategoriesSubcategories().subscribe(
      (result) => {
        this._categories = result.map(({
                                         id,
                                         name,
                                         nameSr,
                                         subcategories
                                       }) => new Category(
          name,
          nameSr,
          id,
          subcategories
        ));
        const subCategories: Subcategory[] = [];

        result.map((category) => {
          category.subcategories.map((subcategory: Subcategory) => {
            return subCategories.push(new Subcategory(subcategory.name, subcategory.nameSr, subcategory.id, undefined, category));
          });
        });

        this._subCategories = subCategories;
      },
      (err) => this.errorLoading = true);
  }

  public loadSubcategories(): void {
    this.categoryService.getAllSubcategories().subscribe(
      (result) => {
        this._subCategories = result.map(({
                                            id,
                                            name,
                                            nameSr,
                                          }) => new Subcategory(
          name,
          nameSr,
          id,
        ));

      },
      (err) => this.errorLoading = true);
  }

  public deleteSubcategory(subcategory: Subcategory, category: Category) {
    this.categoryService.deleteSubcategory(subcategory, category).subscribe(
      (result) => {
        this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.deleteSubcategory || '', 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
        this.loadAdminSubcategories();
      },
      (err) => {
        this.errorLoading = true;
        this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      });
  }

  public editCategory(category: Category) {
    this.categoryService.editCategory(category).subscribe(res => {
    }, err => {
      this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this.loadCategories();
      this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.categoryEditedName, ['name'], [category.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public deleteCategory(category: Category) {
    this.categoryService.deleteCategory(category.name).subscribe(res => {
    }, err => {
      this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this.loadCategories();
      this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.categoryDeleted, ['name'], [category.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public addCategory(category: Category) {
    this.categoryService.addCategory(category).subscribe(res => {
    }, err => {
      this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.categoryAdded, ['name'], [category.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public addSubcategory(subcategory: Subcategory, categoryName: string) {
    this.categoryService.addSubcategory(subcategory, categoryName).subscribe(res => {
    }, err => {
      this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.subcategoryAdded, ['name'], [subcategory.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public editSubcategory(subcategory: Subcategory, categoryName: string) {
    this.categoryService.editSubcategory(subcategory, categoryName).subscribe(res => {
    }, err => {
      this._snackBar.open(this.commonLanguageModel.currentLanguagePackage()?.errorSthWrong || '', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open(this.commonLanguageModel.languageReplace(this.commonLanguageModel.currentLanguagePackage()?.subcategoryEditedName, ['name'], [subcategory.name]), 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
      this.loadAdminSubcategories();
    });
  }

  public setSubcategory(catName: string) {
    const category = this.categories.find(({subcategories}) => subcategories.find(({name}) => catName === name));
    if (category) {
      const subcategory = category.subcategories.find(({name}) => catName === name);
      if (subcategory) {
        this._subCategory = subcategory;
      }
    }
  }

}
