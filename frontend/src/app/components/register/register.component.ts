import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {TokenService} from '../../services/token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSuccessful: boolean;
  isSignUpFailed: boolean;
  errorMessage: any;

  constructor(private authService: AuthService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.tokenService.isLoggedIn()) {
      this.isSuccessful = true;
    }
  }

  register(): void {
    this.authService.register(this.form).subscribe((response: any) => {
        if ('token' in response) {
          this.tokenService.saveToken(response.token);
          this.tokenService.saveUser(this.form.username);
          this.isSuccessful = true;
          this.isSignUpFailed = false;
        } else {
          this.handleNoSuccess('No token present');
        }
      },
      err => {
        this.handleNoSuccess(err);
      }
    );
  }

  private handleNoSuccess(err): void {
    console.log(err);
    this.errorMessage = err.error.message;
    this.isSignUpFailed = true;
  }
}
