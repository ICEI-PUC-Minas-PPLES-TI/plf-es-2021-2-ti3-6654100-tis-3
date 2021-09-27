import { FormControl, FormGroup } from '@angular/forms';

export const checkEmail = (control: FormControl) => {
  if (control) {
    const value: string = control.value;
    const re =
      // eslint-disable-next-line max-len
      /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(String(value))) {
      return { email: 'true' };
    }
  }
  return null;
};

export const checkConfirmPassword = (formGroup: FormGroup) => {
  if (!!formGroup) {
    const password: string = formGroup.get('password')
      ? formGroup.get('password').value
      : '';
    const confirmPassword: string = formGroup.get('confirmPassword').value;
    if (password === confirmPassword) {
      return null;
    }
  }
  return { confirmPassword: 'true' };
};
