import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.page.html',
  styleUrls: ['./bills.page.scss'],
})
export class BillsPage implements OnInit {

  addTransaction: FormGroup = new FormGroup(
    {
      transactionType: new FormControl(null, [
        Validators.required,
      ]),
      transactionValue: new FormControl(null, [
        Validators.required,
      ]),
      relatedProduct: new FormControl(false),
      productName: new FormControl(),
      productQuantity: new FormControl()
    },
  );

  transactionModal: HTMLElement;

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {
    this.transactionModal = document.getElementById('add-transaction');
    this.checkUserLogged();
  }

  checkUserLogged() {
    if (window.localStorage.getItem('userLogged') !== 'true') {
      this.router.navigate(['external/login']);
    }
  }

  toggleTransactionModal() {
    this.transactionModal.classList.toggle('hidden');
    if (this.transactionModal.classList.contains('hidden')) {
      this.addTransaction.reset();
    }
  }

  toggleAccordion(id: string) {
    console.log('abre aí', id);
    const element = document.getElementById(id);
    const content = Array.from(element.children).filter(e => e.classList.contains('content'));
    content[0].classList.toggle('hidden');
  }

  onAddTransaction() {
    console.log('ainda preciso fazer isso kkkkkkrying');
    console.log('🚀 -> BillsPage -> addTransaction', this.addTransaction.value);
  }

  preventDefault($event) {
    $event.preventDefault();
    $event.stopPropagation();
  }
}

