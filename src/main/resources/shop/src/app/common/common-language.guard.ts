import {Injectable} from '@angular/core';
import {AbstractLanguageGuard} from './abstract-language.guard';
import {CommonLanguageModel} from './common-language.model';

@Injectable()
export class CommonLanguageGuard extends AbstractLanguageGuard {

    constructor(protected commonLanguageModel: CommonLanguageModel) {
        super(commonLanguageModel);
    }
}
