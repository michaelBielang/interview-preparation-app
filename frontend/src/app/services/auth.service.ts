import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Token} from '../enums/token';

const registerRoute = 'http://localhost:8080/api/register';
const loginRoute = 'http://localhost:8080/api/login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private client: HttpClient) {
  }

  static getLoginHeader(): any {
    const header = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return header;
  }

  static getLoginBody(user): any {
    const body = {
      email: user.email,
      password: user.password
    };
    return body;
  }

  private static generateRegisterHeader(user): any {
    return {
      headers: new HttpHeaders({'Content-Type': 'application/json'}),
      params: new HttpParams()
        .append('username', user.username)
        .append('password', user.password)
        .append('email', user.email)
    };
  }

  register(user): Observable<HttpEvent<Token>> {
    const httpHeader = AuthService.generateRegisterHeader(user);
    return this.client.post<Token>(registerRoute, {}, httpHeader);
  }

  login(user): Observable<HttpEvent<Token>> {
    const body = AuthService.getLoginBody(user);
    const header = AuthService.getLoginHeader();
    return this.client.post<Token>(loginRoute, body, header);
  }
}
