import {Injectable} from '@angular/core';
import {AbstractLanguageGuard} from './abstract-language.guard';
import {CommonLanguageModel} from './common-language.model';
import {AuthService} from '../services/auth.service';

@Injectable()
export class CommonLanguageGuard extends AbstractLanguageGuard {

    constructor(protected commonLanguageModel: CommonLanguageModel,
                protected _authService: AuthService) {
        super(commonLanguageModel, _authService);
    }
}
