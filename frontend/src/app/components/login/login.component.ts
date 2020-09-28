import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {TokenService} from '../../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn: boolean;
  loginFailed: boolean;
  errorMessage: any;

  constructor(private authService: AuthService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLoggedIn = true;
    }
  }

  login(): void {
    this.authService.login(this.form).subscribe(
      (response: any) => {
        if ('token' in response) {
          this.tokenService.saveToken(response.token);
          this.tokenService.saveUser(this.form.username);

          this.isLoggedIn = true;
          this.reloadPage();
        } else {
          this.handleLoginError('No token received');
        }
      },
      err => {
        this.handleLoginError(err);
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  private handleLoginError(err): void {
    this.errorMessage = err.error.message;
    this.loginFailed = true;
  }
}
