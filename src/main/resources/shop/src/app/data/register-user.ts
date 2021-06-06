import {LanguageType} from '../common/abstract-language.model';

export class RegisterUser {
  username: string;
  password: string;
  language: LanguageType;
  code: string | undefined;
  firma: string | undefined;
  grad: string | undefined;
  mb: string | undefined;
  pib: string | undefined;
  rabat: number | undefined;
  role: string | undefined;
  ulica: string | undefined;

  constructor(username: string,
              password: string,
              language: LanguageType,
              code?: string,
              firma?: string,
              grad?: string,
              mb?: string,
              pib?: string,
              rabat?: number,
              role?: string,
              ulica?: string,) {
    this.username = username;
    this.password = password;
    this.language = language.toUpperCase() as LanguageType;
    if (code) {
      this.code = code;
    }
    if (firma) {
      this.firma = firma;
    }
    if (grad) {
      this.grad = grad;
    }
    if (mb) {
      this.mb = mb;
    }
    if (pib) {
      this.pib = pib;
    }
    if (rabat) {
      this.rabat = parseFloat((rabat / 100).toFixed(2));
    }
    if (role) {
      this.role = role;
    }
    if (ulica) {
      this.ulica = ulica;
    }
  }
}
