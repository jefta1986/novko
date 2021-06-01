import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function ConfirmedValidator(controlName: string, matchingControlName: string): ValidatorFn | null {
  return (controls: AbstractControl): ValidationErrors | null => {
    const control = controls.get(controlName)?.value;
    const matchingControl = controls.get(matchingControlName)?.value;
    if (matchingControl === '' || control === '') {
      return null;
    }
    if (control !== matchingControl) {
      return matchingControl.setErrors({confirmedValidator: true});
    }
    return matchingControl.setErrors(null);
  };
}
