import {Injectable} from '@angular/core';
import {CommonLanguageService} from './common-language.service';
import {CommonLanguageInterface} from './common-language.interface';
import {AbstractLanguageModel, LanguageType, LanguageTypes} from './abstract-language.model';

@Injectable()
export class CommonLanguageModel extends AbstractLanguageModel {

  public availableLanguages: LanguageType[] = [LanguageTypes.SR, LanguageTypes.EN];
  public currentLanguage: LanguageType | null = null;


  constructor(protected _commonLanguageService: CommonLanguageService) {
    super(_commonLanguageService);
  }

}
