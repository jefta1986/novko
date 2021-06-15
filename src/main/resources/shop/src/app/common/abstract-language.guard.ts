import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {AbstractLanguageModel, LanguageType} from './abstract-language.model';
import {AuthService} from '../services/auth.service';

@Injectable()
export class AbstractLanguageGuard implements CanActivate {

  constructor(protected abstractLanguageModel: AbstractLanguageModel,
              protected _authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> | boolean {
    let defaultLang: LanguageType;
    if (this._authService.user) {
      defaultLang = this._authService.user.language.toLowerCase() as LanguageType;
    } else {
      defaultLang = this.abstractLanguageModel.availableLanguages[0];
    }

    if (this.abstractLanguageModel.hasLanguagePack(defaultLang)) {
      return true;
    }

    return this.abstractLanguageModel
      .loadLanguagePackage(defaultLang)
      .then(() => true)
      .catch((err) => {
        console.log(err);
        return false;
      });
  }
}
