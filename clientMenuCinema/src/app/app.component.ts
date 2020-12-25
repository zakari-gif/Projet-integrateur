import {Component} from '@angular/core';
import {TmdbService} from './tmdb.service';
import {environment} from '../environments/environment';
import {NavBarService} from './nav-bar-service';
import {Router} from '@angular/router';
import {ListeFilmsService} from './liste-films.service';
import {ListePlatsService} from './liste-plats.service';
import {PanierService} from './panier.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss', '../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class AppComponent {
  constructor(private tmdb: TmdbService,
              public nav: NavBarService,
              private route: Router,
              private listeFilms: ListeFilmsService,
              private listePlats: ListePlatsService,
              private panier: PanierService) {
    this.init();
    this.route.events.subscribe( r => this.updateNavVisibility());
  }

  async init() {
    this.tmdb.init( environment.tmdbKey );
    this.listeFilms.init();
    this.panier.init();
  }

  updateNavVisibility() {
    const currPath = window.location.pathname;
    if ( currPath !== '/') {
      this.nav.show();
    } else {
      this.nav.hide();
    }
  }
}
