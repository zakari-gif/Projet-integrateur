import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ClientService} from '../client.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class NavbarComponent implements OnInit {
  visible: boolean;
  constructor(private r: Router, private cs: ClientService) {
    const currPath = window.location.pathname;
    if ( currPath !== '/' && currPath !== '/register' ) {
      this.visible = true;
    } else {
      this.visible = false;
    }
  }

  ngOnInit(): void {

  }

  deco() {
    this.cs.logout();
  }
}
