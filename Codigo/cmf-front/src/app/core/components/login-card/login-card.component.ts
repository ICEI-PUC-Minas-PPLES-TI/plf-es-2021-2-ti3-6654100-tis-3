import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TopbarService } from 'src/app/services/topbar.service';

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.scss'],
})
export class LoginCardComponent implements OnInit {

  constructor(
    private router: Router,
    private topbarService: TopbarService,
  ) { }

  ngOnInit() {
    this.topbarService.configBackButton(false);
  }

  navigateToRegister() {
    this.router.navigate(['/external/register']);
  }

}
