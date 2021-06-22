import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {CategoriesModel} from '../data/models/categories.model';
import {Subcategory} from '../data/subcategory';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent extends CommonAbstractComponent implements OnInit {

  public get subcategories(): Subcategory[] {
    return this._categoriesModel.subCategories;
  }

  public get isSerbian(): boolean {
    return this.commonLanguageModel.currentLanguage === 'sr';
  }

  public currentYear = new Date().getFullYear();

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel,
              private _categoriesModel: CategoriesModel,
              public _authService: AuthService) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
  }

}
