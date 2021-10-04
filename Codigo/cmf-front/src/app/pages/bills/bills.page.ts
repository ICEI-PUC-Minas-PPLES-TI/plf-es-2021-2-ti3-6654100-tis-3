import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController, ToastController } from '@ionic/angular';
import { TransactionService } from 'src/app/services/transaction.service';
import { UserService } from 'src/app/services/user.service';
import { DEFAULT_TRANSACTION, Transaction } from 'src/app/types/Transaction';

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
  newTransaction: Transaction = DEFAULT_TRANSACTION;

  availableProducts = [];

  constructor(
    private router: Router,
    private loadingController: LoadingController,
    private toastController: ToastController,
    private transactionService: TransactionService,
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.transactionModal = document.getElementById('add-transaction');
    this.checkUserLogged();

    this.availableProducts = ['a', 'b', 'c'];
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

  async onAddTransaction() {
    const l = await this.loadingController.create({
      message: 'Adicionando movimentação...',
    });
    l.present();

    try {
      this.newTransaction = {
        userId: (await this.userService.getUserByEmail('johndoe@ceo.com')).idUsuario,
        order: this.addTransaction.get('transactionType').value,
        type: this.addTransaction.get('relatedProduct').value ? 'PRODUTO' : 'CONTAS',
        value: this.addTransaction.get('transactionValue').value,
      };

      const response = await this.transactionService.createTransaction(this.newTransaction);
      console.log('🚀 -> BillsPage -> onAddTransaction -> response', response);
      l.dismiss();

      const t = await this.toastController.create({
        message: 'Movimentação criada com sucesso!',
        duration: 4000,
        color: 'success',
      });
      t.present();

    } catch (error) {
      l.dismiss();

      const t = await this.toastController.create({
        message: 'Falha na criação da movimentação, por favor verifique os dados e tente novamente.',
        duration: 4000,
        color: 'danger',
      });
      t.present();
      console.error('🚀 -> BillsPage -> onAddTransaction -> error', error);
      throw error;
    }
  }

  preventDefault($event) {
    $event.preventDefault();
    $event.stopPropagation();
  }
}

