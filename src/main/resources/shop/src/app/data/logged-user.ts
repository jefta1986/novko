import {LanguageType} from '../common/abstract-language.model';

export class LoggedUser {
  active: boolean;
  code: string;
  firma: string;
  grad: string;
  id: number;
  language: LanguageType;
  mb: string;
  pib: string;
  rabat: number;
  role: string;
  ulica: string;
  username: string;

  constructor(active: boolean,
              code: string,
              firma: string,
              grad: string,
              id: number,
              language: LanguageType,
              mb: string,
              pib: string,
              rabat: number,
              role: string,
              ulica: string,
              username: string,) {
    this.active = active;
    this.code = code;
    this.firma = firma;
    this.grad = grad;
    this.id = id;
    this.language = language.toLowerCase() as LanguageType;
    this.mb = mb;
    this.pib = pib;
    this.rabat = rabat;
    this.role = role;
    this.ulica = ulica;
    this.username = username;
  }
}
