import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  showSpinner: any;
  private isSuccessful: boolean;
  private isSignUpFailed: boolean;
  private errorMessage: any;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  register(): void {
    this.authService.register(this.form).subscribe(data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
