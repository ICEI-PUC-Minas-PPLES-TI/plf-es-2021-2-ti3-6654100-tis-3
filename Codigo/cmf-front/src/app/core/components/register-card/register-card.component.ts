import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DEFAULT_USER, User } from 'src/app/types/User';

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.scss'],
})
export class RegisterCardComponent implements OnInit {

  registerForm: FormGroup = new FormGroup(
    {
      email: new FormControl(null, [
        Validators.required,
        this.checkEmail,
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(6)
      ])
    }
  );

  newUser: User = DEFAULT_USER;

  constructor() { }

  ngOnInit() {
    console.log('to aquiiiii');
  }

  onSubmit() {
    console.log('submit pressed');
    console.log('🚀 -> RegisterCardComponent -> email', this.newUser.email);
    console.log('🚀 -> RegisterCardComponent -> password', this.newUser.password);
    window.alert(`sending new user to register endpoint\nemail=${this.newUser.email}\npassword=${this.newUser.password}`);
  }

  checkEmail(control: FormControl) {
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
  }

}
