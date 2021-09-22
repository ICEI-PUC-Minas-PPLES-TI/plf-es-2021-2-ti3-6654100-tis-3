/* eslint-disable @typescript-eslint/naming-convention */
import { Injectable } from '@angular/core';
import { User } from '../types/User';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private http: HttpClient,
  ) {}

  async createUser(user: User) {
    console.log('🚀 -> UserService -> createUser -> user', user);

    const url = `${environment.BASE_URL}/usuario/cadastrar`;
    const body = user;
    const headers = new HttpHeaders({
      Accept: '*/*'
    });
    try {
      const response = await this.http
      .post<any>(url, body, { headers })
      .toPromise();

      console.log('🚀 -> UserService -> createUser -> response', response);
    } catch (error) {
      console.error('ERROR on user-service:', error);
      throw error;
    }
  }
}
