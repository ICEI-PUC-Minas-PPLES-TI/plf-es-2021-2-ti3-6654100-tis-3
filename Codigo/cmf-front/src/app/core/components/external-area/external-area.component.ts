import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-external-area',
  templateUrl: './external-area.component.html',
  styleUrls: ['./external-area.component.scss'],
})
export class ExternalAreaComponent implements OnInit {
  constructor() {}

  ngOnInit() {
    console.log('cheguei no componente external-area');
  }
}
