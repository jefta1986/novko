import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonLanguageInterface } from './common-language.interface';
import { AbstractLanguageService } from './abstract-language.service';
import { LanguageType } from './abstract-language.model';

@Injectable()
export class CommonLanguageService extends AbstractLanguageService {

    protected translateFileLocation = `/assets/i18-common/`;

    constructor(protected httpClient: HttpClient) {
        super(httpClient);
    }

    public loadLanguagePackage(lang: LanguageType): Promise<CommonLanguageInterface> {
        return super.loadLanguagePackage(lang);
    }

}
