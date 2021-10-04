import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Transaction, TransactionResponse } from '../types/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(
    private http: HttpClient,
  ) { }

  async createTransaction(transaction: Transaction): Promise<TransactionResponse> {
    console.log('🚀 -> TransactionService -> createTransaction -> params', transaction);
    const url = `${environment.BASE_URL}/movimentacao/cadastrar`;
    const body = {
      idProduto: transaction.productId ? transaction.productId : 0,
      idUsuario: transaction.userId,
      ordem: transaction.order,
      tipoMovimentacao: transaction.type,
      valor: transaction.value
    };

    try {
      const response = await this.http
      .post<TransactionResponse>(url, body)
      .toPromise();
      return response;
    } catch (error) {
      this.logError(error);
      throw error;
    }
  }

  logError(error: Error) {
    console.error('ERROR on transaction-service:', error);
  }
}
