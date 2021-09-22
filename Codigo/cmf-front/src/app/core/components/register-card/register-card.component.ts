import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoadingController } from '@ionic/angular';
import { UserService } from 'src/app/services/user.service';
import { DEFAULT_USER, User } from 'src/app/types/User';

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.scss'],
})
export class RegisterCardComponent implements OnInit {

  registerForm: FormGroup = new FormGroup(
    {
      name: new FormControl(null, [
        Validators.required,
      ]),
      role: new FormControl(null, [
        Validators.required,
      ]),
      email: new FormControl(null, [
        Validators.required,
        this.checkEmail,
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(6)
      ]),
      confirmPassword: new FormControl(null, [
        Validators.required,
        Validators.minLength(6),
        // this.checkConfirmPassword,
      ])
    }
  );

  newUser: User = DEFAULT_USER;

  constructor(
    private userService: UserService,
    private loadingController: LoadingController,
  ) { }

  ngOnInit() {
    console.log('to aquiiiii register-card');
  }

  async onSubmit() {
    console.log('submit pressed');
    const email: string = this.registerForm.get('email').value;
    const password: string = this.registerForm.get('password').value;
    console.log('🚀 -> RegisterCardComponent -> email', email);
    console.log('🚀 -> RegisterCardComponent -> password', password);

    const loading = await this.loadingController.create({
      message: 'Cadastrando usuário',
    });

    loading.present();

    try {
      this.newUser = {
        name: this.registerForm.get('name').value,
        email: this.registerForm.get('email').value,
        password: this.registerForm.get('password').value,
        role: this.registerForm.get('role').value,
      };

      await this.userService.createUser(this.newUser);
      loading.dismiss();
    } catch (error) {
      loading.dismiss();
      throw error;
    }
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

  checkConfirmPassword(confirmPasswordControl: FormControl) {
    if (!!confirmPasswordControl && !!this.registerForm) {
      const password: string = this.registerForm.get('password') ? this.registerForm.get('password').value : '';
      const confirmPassword: string = confirmPasswordControl.value;
      if (password === confirmPassword) {
        return { confirmPassword: 'true'};
      }
    }
    return null;
  }

}
