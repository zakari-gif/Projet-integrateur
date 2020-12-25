import {Component, Input, OnInit} from '@angular/core';
import {MovieResponse} from '../tmdb-data/Movie';
import {Router} from '@angular/router';
import {ClientService} from '../client.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {PanierService} from '../panier.service';

@Component({
  selector: 'app-affiche-film',
  templateUrl: './affiche-film.component.html',
  styleUrls: ['./affiche-film.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class AfficheFilmComponent implements OnInit {

  @Input() film: MovieResponse;
  constructor(private r: Router,
              private cs: ClientService,
              private snackBar: MatSnackBar,
              private panierService: PanierService) { }

  ngOnInit(): void {
  }

  goDetail(event) {
    if (event.target.tagName !== 'BUTTON') {
      this.r.navigate(['/film', this.film.id]);
    }
  }

  ajouterAuPanier() {
     this.snackBar.open(this.panierService.addFilm(this.film), 'OK', { duration: 3000 });
  }
}
