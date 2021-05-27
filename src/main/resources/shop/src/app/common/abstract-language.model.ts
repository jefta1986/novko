import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { AbstractLanguageService } from './abstract-language.service';
import { SignalOne } from '../libs/signals/SignalOne';
import { Signal } from '../libs/signals/Signal';

export type LanguageType = 'en' | 'sr';

interface LanguageTypesInterface {
    EN: LanguageType;
    SR: LanguageType;
}

export const LanguageTypes: LanguageTypesInterface = {
    EN: 'en',
    SR: 'sr',
};

@Injectable()
export abstract class AbstractLanguageModel {
    public readonly onLanguageChange: SignalOne<LanguageType> = new SignalOne<LanguageType>();
    public readonly onLanguageError: Signal = new Signal();

    public abstract availableLanguages: LanguageType[] = [];
    public abstract currentLanguage: LanguageType = null;
    public languagePackages: Map<LanguageType, any> = new Map<LanguageType, any>();

    protected constructor(protected languageService: AbstractLanguageService) {
    }

    public changeLanguage(language: LanguageType): Promise<LanguageType | Error> {
        if (this.hasLanguagePack(language) === false) {
            return this.loadLanguagePackage(language)
                .catch(err => {
                    console.log(err);
                    return Promise.reject(err);
                });
        } else {
            return Promise.resolve(this.setCurrentLanguage(language));
        }
    }

    public currentLanguagePackage(): any {
        return this.languagePackages.get(this.currentLanguage);
    }

    public loadLanguagePackage(language: LanguageType): Promise<LanguageType | Error> {
        return this.languageService
            .loadLanguagePackage(language)
            .then((response: any) => this.onLanguagePackageLoad(language, response))
            .catch((err: HttpErrorResponse) => this.onLanguagePackageError(err));
    }

    public hasLanguagePack(language: LanguageType): boolean {
        return this.languagePackages.has(language);
    }

    public isLanguageAvailable(language: LanguageType): boolean {
        return this.availableLanguages.indexOf(language) !== -1;
    }

    private onLanguagePackageLoad(language: LanguageType, languagePackage: any): LanguageType {
        this.languagePackages.set(language, languagePackage);
        return this.setCurrentLanguage(language);
    }

    private setCurrentLanguage(language): LanguageType {
        this.currentLanguage = language;
        this.onLanguageChange.dispatch(this.currentLanguage);
        return this.currentLanguage;
    }

    private onLanguagePackageError(err: HttpErrorResponse): Promise<HttpErrorResponse> {
        this.onLanguageError.dispatch();
        console.log(err);
        return Promise.reject(err);
    }


}
