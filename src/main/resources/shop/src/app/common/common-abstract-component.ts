import {ChangeDetectorRef, Component} from '@angular/core';
import {CommonLanguageInterface} from './common-language.interface';
import {CommonLanguageModel} from './common-language.model';
import {AbstractComponent} from './abstract-component';

@Component({
  template: ''
})
export abstract class CommonAbstractComponent extends AbstractComponent {

  public get language(): CommonLanguageInterface {
    return super.getCurrentLanguagePackage();
  }

  protected constructor(protected cdr: ChangeDetectorRef,
                        protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  public languageReplace(string: string, keyValues: string[], values: any[]): string {
    return this.commonLanguageModel.languageReplace(string, keyValues, values);
  }

}


