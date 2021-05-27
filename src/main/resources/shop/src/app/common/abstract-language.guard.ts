import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { AbstractLanguageModel, LanguageType } from './abstract-language.model';

@Injectable()
export class AbstractLanguageGuard implements CanActivate {

    constructor(protected abstractLanguageModel: AbstractLanguageModel) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> | boolean {
        const defaultLang: LanguageType = this.abstractLanguageModel.availableLanguages[0];
        if (this.abstractLanguageModel.hasLanguagePack(defaultLang) === true) {
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
