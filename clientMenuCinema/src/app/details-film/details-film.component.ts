import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {MovieResponse} from '../tmdb-data/Movie';
import {TmdbService} from '../tmdb.service';
import {ClientService} from '../client.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {PanierService} from "../panier.service";

@Component({
  selector: 'app-details-film',
  templateUrl: './details-film.component.html',
  styleUrls: ['./details-film.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class DetailsFilmComponent implements OnInit {
  film: MovieResponse;
  constructor(private tmdb: TmdbService,
              private route: ActivatedRoute,
              private cs: ClientService,
              private snackBar: MatSnackBar,
              private panierService: PanierService) {
    this.cs.routingNotConnected();
  }

  ngOnInit(): void {
    this.init();
  }

  async init() {
    this.film = await this.tmdb.getMovie(
      this.route.snapshot.params.id,
      {
        language: 'fr'
      }
    );
  }

  get runtime(): string {
    return  (
      (this.film.runtime - ( this.film.runtime % 60 )) / 60 )
      + 'h ' +
      ( this.film.runtime % 60 ).toString().padStart(2, '0') + 'min';
  }

  ajouterAuPanier() {
    this.snackBar.open(this.panierService.addFilm(this.film), 'OK', { duration: 3000 });
  }
}
