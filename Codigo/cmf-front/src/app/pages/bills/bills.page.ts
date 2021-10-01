import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.page.html',
  styleUrls: ['./bills.page.scss'],
})
export class BillsPage implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {
    this.checkUserLogged();
  }

  checkUserLogged() {
    if (window.localStorage.getItem('userLogged') !== 'true') {
      this.router.navigate(['external/login']);
    }
  }
}

