import {Component, OnInit} from '@angular/core';
import {TokenService} from '../../services/token.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private tokenService: TokenService) {
  }

  ngOnInit(): void {
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

}
