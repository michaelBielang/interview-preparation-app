import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  user = [{}];
  showSpinner: any;

  constructor() {
  }

  ngOnInit(): void {
  }

  onClick(): void {

  }

  register(): void {
    console.log(this.username);
    console.log(this.password);
  }
}
