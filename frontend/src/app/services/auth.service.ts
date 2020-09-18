import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

const registerRoute = 'http://localhost:8080/api/register';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private client: HttpClient) {
  }

  register(user): Observable<any> {
    const httpHeader = this.gnerateHeader(user);
    return this.client.post(registerRoute, {}, httpHeader);
  }

  private gnerateHeader(user): any {
    const httpHeader = {
      headers: new HttpHeaders({'Content-Type': 'application/json'}),
      params: new HttpParams()
        .append('username', user.username)
        .append('password', user.password)
        .append('email', user.email)
    };
    return httpHeader;
  }
}
