import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import { Client } from '../client-data/Client';
import {AngularFireAuth} from '@angular/fire/auth';
import {TmdbService} from "../tmdb.service";

@Component({
  selector: 'app-mon-compte',
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class MonCompteComponent implements OnInit {

  constructor(private cs: ClientService) {
    this.cs.routingNotConnected();
  }

  ngOnInit(): void {
  }

  get client() {
    return this.cs.getClient();
  }

  get AdresseDefined(): boolean {
    return this.cs.clientAdresseIsDefined();
  }
  isNull(value: string): string {
    return value === 'null' ? 'Non renseigné' : value;
  }

  get displayAdr(): string {
    return this.cs.adrDisplay;
  }
}
