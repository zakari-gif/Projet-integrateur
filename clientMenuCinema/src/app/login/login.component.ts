import {Component, Inject, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth, User} from 'firebase/app';
import {HttpClient, HttpErrorResponse  } from '@angular/common/http';
import {ClientService} from '../client.service';
import {SESSION_STORAGE, StorageService} from 'ngx-webstorage-service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class LoginComponent implements OnInit {

  constructor(private cs: ClientService) {
  }

  ngOnInit(): void {

  }

  loginGoogle() {
    this.cs.loginGoogle();
  }


  get isConnected(): boolean {
    return this.cs.isConnected;
  }

}
