import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController, ToastController } from '@ionic/angular';
import { TopbarService } from 'src/app/services/topbar.service';
import { UserService } from 'src/app/services/user.service';
import { checkEmail } from '../../shared/custom-validators/custom-validators-sign-up-form';

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.scss'],
})
export class LoginCardComponent implements OnInit {

  loginForm: FormGroup = new FormGroup(
    {
      email: new FormControl(null, [
        Validators.required,
        checkEmail,
      ]),
      password: new FormControl(null, [
        Validators.required,
      ]),
    },
  );

  isMobile: boolean;

  constructor(
    private router: Router,
    private topbarService: TopbarService,
    private userService: UserService,
    private loadingController: LoadingController,
    private toastController: ToastController,
  ) { }

  @HostListener('window:resize')
  onResize() {
    this.isMobile = window.innerWidth < 768;
  }

  ngOnInit() {
    this.isMobile = window.innerWidth < 767;
    this.topbarService.configBackButton(false);
  }

  navigateToRegister() {
    this.router.navigate(['/external/register']);
  }

  async onSubmit() {
    console.log('submit login');

    const email = this.loginForm.get('email').value;
    const password = this.loginForm.get('password').value;

    const loading = await this.loadingController.create({
      message: 'Entrando',
    });

    loading.present();

    try {
      const response = await this.userService.login(email, password);
      if (response) {
        this.showToast('Usuário autenticado com sucesso!', 'success');
        this.router.navigate(['/bills']);
      } else {
        this.showToast('Email ou senha incorretos, por favor tente novamente.', 'danger');
      }
      loading.dismiss();

    } catch (error) {
      loading.dismiss();
      this.showToast('Ocorreu um erro ao tentar fazer login, por favor tente novamente.', 'danger');
      throw error;
    }
  }

  async showToast(message: string, color: string) {
    const t = await this.toastController.create({
      message,
      color,
      duration: 4000,
    });
    t.present();
  }

}
