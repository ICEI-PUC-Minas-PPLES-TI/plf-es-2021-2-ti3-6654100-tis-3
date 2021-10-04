import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController, ToastController } from '@ionic/angular';
import { ProductService } from 'src/app/services/product.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { UserService } from 'src/app/services/user.service';
import { ProductResponse } from 'src/app/types/Product';
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
  transactionModal1: HTMLElement;
  newTransaction: Transaction = DEFAULT_TRANSACTION;

  availableProducts: ProductResponse[];

  feesMapping = {
    workers: 1,
    taxes: 2,
    products: 3,
    total: 6
  };

  constructor(
    private router: Router,
    private loadingController: LoadingController,
    private toastController: ToastController,
    private transactionService: TransactionService,
    private userService: UserService,
    private productService: ProductService,
  ) { }

  async ngOnInit() {
    this.transactionModal = document.getElementById('add-transaction');
    this.transactionModal1 = document.getElementById('add-transaction1');
    this.checkUserLogged();

    this.availableProducts = await this.productService.getAvailableProducts();
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

  toggleTransactionModal1() {
    this.transactionModal1.classList.toggle('hidden');
  }


  toggleAccordion(id: string) {
    console.log('abre aí', id);
    const content = document.getElementById(id + '-content');
    content.classList.toggle('hidden');
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
        value: this.addTransaction.get('transactionValue').value,
        type: this.addTransaction.get('relatedProduct').value ? 'PRODUTO' : 'CONTAS',
        productId: this.addTransaction.get('relatedProduct').value ? this.addTransaction.get('productName').value : null,
        productQty: this.addTransaction.get('relatedProduct').value ? this.addTransaction.get('productQuantity').value : null,
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

