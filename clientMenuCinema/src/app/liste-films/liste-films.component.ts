import { Component, OnInit} from '@angular/core';
import {ClientService} from '../client.service';
import {ListeFilmsService} from '../liste-films.service';
import {DiscoverMovieResponse} from '../tmdb-data/DiscoverMovie';
import {SearchMovieResponse} from '../tmdb-data/searchMovie';

@Component({
  selector: 'app-liste-films',
  templateUrl: './liste-films.component.html',
  styleUrls: ['./liste-films.component.scss', '../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class ListeFilmsComponent implements OnInit {
  listeFilms: DiscoverMovieResponse|SearchMovieResponse;
  nbPage: number;
  query: string;
  constructor(private cs: ClientService, private listeFilmsServ: ListeFilmsService) {
    this.cs.routingNotConnected();
    this.listeFilmsServ.listeFilmsObs.subscribe(
      (lf) => {
        this.listeFilms = lf;
        this.nbPage = lf.page;
      }
    );
    this.query = this.listeFilmsServ.query;
  }
  ngOnInit(): void {
  }

  clearSearch() {
    this.query = '';
    this.listeFilmsServ.updatePageDiscMovie(this.listeFilmsServ.pageDisc);
  }

  search() {
    if (this.query.length >= 3) {
      this.listeFilmsServ.updatePageSearchMovie(1, this.query);
    }
  }

  changePage() {
    this.verifPage();
    if (this.isDiscover) {
      this.listeFilmsServ.updatePageDiscMovie(this.nbPage);
    } else {
      this.listeFilmsServ.updatePageSearchMovie(this.nbPage, this.query);
    }
  }

  haveResults(): boolean {
    return this.films.length > 0;
  }

  verifPage() {
    if (this.nbPage < 1) {
      this.nbPage = 1;
    } else if (this.nbPage > this.listeFilms.total_pages) {
      this.nbPage = this.listeFilms.total_pages;
    }
  }

  get films() {
    return this.listeFilms.results;
  }

  get isDiscover(): boolean {
    return this.listeFilmsServ.isDiscover;
  }
}
