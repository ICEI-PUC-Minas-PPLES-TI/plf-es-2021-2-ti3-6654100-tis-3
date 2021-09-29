import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-external-area',
  templateUrl: './external-area.page.html',
  styleUrls: ['./external-area.page.scss'],
})
export class ExternalAreaPage implements OnInit {

  isOnRoot = true;

  constructor(
    private router: Router,
  ) { }

  ngOnInit() { }

  onCLick() {
    this.isOnRoot = this.router.url === '/external/login';
  }

}
