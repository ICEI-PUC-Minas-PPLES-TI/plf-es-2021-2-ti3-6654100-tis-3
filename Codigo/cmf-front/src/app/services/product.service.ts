import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ProductResponse } from '../types/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient,
  ) { }

  async getAvailableProducts(): Promise<ProductResponse[]> {
    const url = `${environment.BASE_URL}/produto/buscar-todos`;

    try {
      const response = await this.http
      .get<ProductResponse[]>(url)
      .toPromise();

      return response;
    } catch (error) {
      this.logError(error);
      throw error;
    }
  }

  logError(error: Error) {
    console.error('ERROR on product-service:', error);
  }
}
