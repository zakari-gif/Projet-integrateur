import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import {Carte} from '../client-data/Carte';
import {Plat} from '../client-data/Plat';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ListePlatsService} from '../liste-plats.service';
import {PanierService} from '../panier.service';

@Component({
  selector: 'app-liste-plats',
  templateUrl: './liste-plats.component.html',
  styleUrls: ['./liste-plats.component.scss']
})
export class ListePlatsComponent implements OnInit {
  hideme = {};
  carte: Carte;
  constructor(private cs: ClientService,
              private snackBar: MatSnackBar,
              private listePlatsServ: ListePlatsService,
              private panierService: PanierService) {
    this.cs.routingNotConnected();
    this.listePlatsServ.init();
    this.listePlatsServ.listePlatsObs.subscribe(
      (c) => {
        this.carte = c;
        this.carte.plats.forEach(
          (p) => {
            this.hideme[p.nom] = false;
          }
        );
      }
    );
  }

  ngOnInit(): void {
  }

  hideIngredients(event, nomPlat: string) {
    if (event.target.tagName !== 'BUTTON' && event.target.tagName !== 'INPUT') {
      this.hideme[nomPlat] = !this.hideme[nomPlat];
    }
  }

  get plats(): Plat[] {
    return this.carte.plats;
  }

  ajouterAuPanier(plat: Plat) {
    this.snackBar.open(this.panierService.addPlat(Object.assign({}, plat)), 'OK', { duration: 3000 });
  }
}
