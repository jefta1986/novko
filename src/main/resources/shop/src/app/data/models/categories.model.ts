import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Category} from '../category';
import {CategoryService} from '../../services/category.service';
import {Subcategory} from '../subcategory';
import {AppConstants} from '../../app-constants';

@Injectable()
export class CategoriesModel {
  private _categories: Category[] = [];
  private _category: Category = this._categories[0];

  private _subCategories: Subcategory[] = [];
  private _subCategory: Subcategory = this._subCategories[0];

  private errorLoading = false;

  public get category(): Category {
    return this._category;
  }

  public get categories(): Category[] {
    return this._categories;
  }

  public get subCategory(): Subcategory {
    return this._subCategory;
  }

  public get subCategories(): Subcategory[] {
    return this._subCategories;
  }

  constructor(private categoryService: CategoryService,
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

  public deleteSubcategory(subcategory: Subcategory) {
    this.categoryService.deleteSubcategory(subcategory).subscribe(
      (result) => {
        this._snackBar.open('Category deleted!', 'Success', {
          duration: 4000,
          panelClass: ['my-snack-bar']
        });
        this.loadSubcategories();
      },
      (err) => {
        this.errorLoading = true;
        this._snackBar.open('Something went wrong,try again!', 'Error', {
          duration: 4000,
          panelClass: ['my-snack-bar-error']
        });
      });
  }

  public editCategory(category: Category) {
    this.categoryService.editCategory(category).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this.loadCategories();
      this._snackBar.open(`Category ${category.name} edited!`, 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public deleteCategory(category: Category) {
    this.categoryService.deleteCategory(category.name).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this.loadCategories();
      this._snackBar.open('Category deleted!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public addCategory(category: Category) {
    this.categoryService.addCategory(category).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open('Category added!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

  public addSubcategory(subcategory: Subcategory, categoryName: string) {
    this.categoryService.addSubcategory(subcategory, categoryName).subscribe(res => {
    }, err => {
      this._snackBar.open('Something went wrong,try again!', 'Error', {
        duration: 4000,
        panelClass: ['my-snack-bar-error']
      });
    }, () => {
      this._snackBar.open('Subcategory added!', 'Success', {
        duration: 4000,
        panelClass: ['my-snack-bar']
      });
    });
  }

}
