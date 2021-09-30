import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopbarService } from 'src/app/services/topbar.service';
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

  onSubmit() {
    console.log('submit login');
  }

}
