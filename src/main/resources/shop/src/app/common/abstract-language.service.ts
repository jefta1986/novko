import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { LanguageType } from './abstract-language.model';

@Injectable()
export abstract class AbstractLanguageService {

    protected abstract translateFileLocation: string = null;

    protected constructor(protected httpClient: HttpClient) {
    }

    public loadLanguagePackage(lang: LanguageType): Promise<any> {
        return this.httpClient
            .get<any>(this.translateFileLocation + lang + '.json')
            .toPromise()
            .catch((err: HttpErrorResponse) => {
                console.log(err);
                return Promise.reject(err);
            });
    }

}
