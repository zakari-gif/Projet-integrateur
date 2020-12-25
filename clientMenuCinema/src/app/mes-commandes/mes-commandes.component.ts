import { Component, OnInit } from '@angular/core';
import {Commande} from '../client-data/Commande';
import {TmdbService} from '../tmdb.service';
import {MovieResponse} from '../tmdb-data/Movie';
import {ClientService} from '../client.service';

@Component({
  selector: 'app-mes-commandes',
  templateUrl: './mes-commandes.component.html',
  styleUrls: ['./mes-commandes.component.scss']
})
export class MesCommandesComponent implements OnInit {
  hideMe = {};
  filmsCom = {};
  commandes: Commande[];
  constructor(private cs: ClientService, private tmdb: TmdbService) {
    this.cs.mesComObs.subscribe(
      (coms) => {
        this.commandes = coms;
        this.commandes.forEach(
          (c) => {
            this.hideMe[c.id] = false;
            this.cs.promiseAllMoviesFromIds(c.films).then(
              (movies) => {
                this.filmsCom[c.id] = movies;
              }
            );
          }
        );
      }
    );
  }

  ngOnInit(): void {
  }

  prixTotalPlatsCom(idCom: number): number {
    const com = this.commandes.filter((c) => c.id === idCom);
    return com[0].plats.reduce((acc, p) => acc + (p.prix * p.quantite), 0);
  }

}
