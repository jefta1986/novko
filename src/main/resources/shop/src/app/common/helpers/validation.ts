import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function ConfirmedValidator(controlName: string, matchingControlName: string): ValidatorFn | null {
  return (controls: AbstractControl): ValidationErrors | null => {
    const controlValue = controls.get(controlName)?.value;
    const matchingControlValue = controls.get(matchingControlName)?.value;
    if (matchingControlValue === '' || controlValue === '') {
      return null;
    }
    if (controlValue !== matchingControlValue) {
      // @ts-ignore
      return controls.get(matchingControlName).setErrors({confirmedValidator: true});
    }
    // @ts-ignore
    return controls.get(matchingControlName).setErrors(null);
  };
}
