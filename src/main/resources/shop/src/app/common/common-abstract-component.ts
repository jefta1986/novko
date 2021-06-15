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
    // @ts-ignore
    let myString: string = string;
    const regexes = keyValues.map(string => new RegExp(`\{${string}\}`, 'gi'));

    for (var i = 0; i < regexes.length; i++) {
      myString = myString.replace(regexes[i], values[i]);
    }

    return myString;
  }

}


