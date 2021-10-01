import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoadingController } from '@ionic/angular';
import { toastController } from '@ionic/core';
import { TopbarService } from 'src/app/services/topbar.service';
import { UserService } from 'src/app/services/user.service';
import { DEFAULT_USER, User } from 'src/app/types/User';
import { checkConfirmPassword, checkEmail } from '../../shared/custom-validators/custom-validators-sign-up-form';

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
        checkEmail,
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(6)
      ]),
      confirmPassword: new FormControl(null, [
        Validators.required,
        Validators.minLength(6),
      ])
    }, { validators: [checkConfirmPassword] }
  );

  newUser: User = DEFAULT_USER;
  isMobile: boolean;

  constructor(
    private userService: UserService,
    private loadingController: LoadingController,
    private topbarService: TopbarService,
  ) { }

  @HostListener('window:resize')
  onResize() {
    this.isMobile = window.innerWidth < 768;
  }

  ngOnInit() {
    this.isMobile = window.innerWidth < 767 ? true : false;
    this.topbarService.configBackButton(true);
  }

  async onSubmit() {
  console.log('🚀 -> RegisterCardComponent -> onSubmit -> submit pressed');

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
      const t = await toastController.create({
        message: 'Conta criada com sucesso!',
        duration: 4000,
        color: 'success'
      });
      t.present();
    } catch (error) {
      loading.dismiss();
      const t = await toastController.create({
        message: 'Ocorreu um erro ao criar sua conta, por favor verifique os dados inseridos e tente novamente',
        duration: 4000,
        color: 'danger'
      });
      t.present();
      throw error;
    }
  }

}
