import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BillsService {

  constructor() { }

  async createBill(params: any) {
    console.log('Not implemented yet');
    console.log('🚀 -> BillsService -> createBill -> params', params);
  }

}
