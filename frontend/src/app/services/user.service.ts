import {Injectable} from '@angular/core';
import {User} from '../enums/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User;

  constructor() {
  }

  getUser(): User {
    return this.user;
  }

  setUser(user: User): void {
    this.user = user;
  }
}
