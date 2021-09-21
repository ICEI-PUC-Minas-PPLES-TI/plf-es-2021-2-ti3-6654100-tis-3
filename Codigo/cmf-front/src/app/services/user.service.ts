import { Injectable } from '@angular/core';
import { User } from '../types/User';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor() {}

  registerUser(user: User) {
    console.log(
      '🚀 -> file: user.service.ts -> line 12 -> UserService -> registerUser -> user',
      user
    );
  }
}
