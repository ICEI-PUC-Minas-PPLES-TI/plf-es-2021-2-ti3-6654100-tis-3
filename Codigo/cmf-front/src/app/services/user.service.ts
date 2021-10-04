/* eslint-disable @typescript-eslint/naming-convention */
import { Injectable } from '@angular/core';
import { DEFAULT_USER, User, UserResponse } from '../types/User';
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
    const url = `${environment.BASE_URL}/usuario/cadastrar`;
    const body = {
      email: user.email,
      nome: user.name,
      senha: user.password
    };

    const headers = new HttpHeaders();

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

  async login(email: string, password: string) {
    const url = `${environment.BASE_URL}/usuario/login`;
    const headers = {
      email,
      senha: password
    };
    let response: boolean;
    try {
      response = await this.http
        .get<boolean>(url, { headers })
        .toPromise();

      console.log('🚀 -> UserService -> login -> response', response);
    } catch (error) {
      if (error.status === 404) {
        response = false;
      } else {
        console.error('ERROR on user-service:', error);
        throw error;
      }
    }
    window.localStorage.setItem('userLogged', response.toString());
    return response;
  }

  async getUserByEmail(email: string): Promise<UserResponse> {
    const url = `${environment.BASE_URL}/usuario/buscar-por-email/{email}?email=${email}`;

    try {
      const response = await this.http
        .get<UserResponse>(url)
        .toPromise();
      return response;
    } catch (error) {
      this.logError(error);
      throw error;
    }
  }

  logError(error: Error) {
    console.error('ERROR on user-service:', error);
  }
}
