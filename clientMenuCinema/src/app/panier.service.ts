import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Panier} from './client-data/Panier';
import {MovieResponse} from './tmdb-data/Movie';
import {Plat} from './client-data/Plat';
import {ClientService} from './client.service';
import {Adresse, Client} from './client-data/Client';
import {HttpClient, HttpErrorResponse, HttpParams, HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Commande} from './client-data/Commande';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  private panier: Panier;
  private panierSubject = new BehaviorSubject<Panier>({films: [], plats: []});
  readonly panierObs = this.panierSubject.asObservable();
  constructor(private cs: ClientService, private http: HttpClient, private snackBar: MatSnackBar) {
    this.panier = this.panierSubject.value;
  }

  get prixPanier() {
    return ( this.panier.films.length * 3.75
      +
      this.panier.plats.reduce( (acc, currPlat) => acc + (currPlat.prix * currPlat.quantite), 0)
    );
  }

  videPanier() {
    this.panierSubject.next({films: [], plats: []});
  }

  init(): this {
    this.videPanier();
    return this;
  }

 listePlatToString(): string {
    let stringListe = '';
    this.panier.plats.forEach(
      (p) => {
        stringListe += p.nom + ',';
      }
    );
    stringListe = stringListe.substring(0, stringListe.length - 1);
    return stringListe;
  }

  listeFilmToString(): string {
    let stringListe = '';
    this.panier.films.forEach(
      (p) => {
        stringListe += p.id + ',';
      }
    );
    stringListe = stringListe.substring(0, stringListe.length - 1);
    return stringListe;
  }

  filmsToNumberArray(): number[] {
    const res = [];
    this.panier.films.forEach(
      (f) => {
        res.push(f.id);
      }
    );
    return res;
  }

  quantitePlatsToString(): string {
    let stringListe = '';
    this.panier.plats.forEach(
      (p) => {
        stringListe += p.quantite + ',';
      }
    );
    stringListe = stringListe.substring(0, stringListe.length - 1);
    return stringListe;
  }

  prixPlatsToString() {
    let stringListe = '';
    this.panier.plats.forEach(
      (p) => {
        stringListe += p.prix + ',';
      }
    );
    stringListe = stringListe.substring(0, stringListe.length - 1);
    return stringListe;
  }

  passerCommande(adr: Adresse) {
    this.POST('/api/addCommande', {
      id: this.cs.getClient().id,
      films: this.listeFilmToString(),
      nomsPlats: this.listePlatToString(),
      quantites: this.quantitePlatsToString(),
      prixPlats: this.prixPlatsToString(),
      numero: adr.numero,
      rue: adr.rue,
      cp: adr.cp,
      ville: adr.ville
    }).then(
      (reponse) => {
        const splitedResponse = reponse.body.split('|');
        this.snackBar.open(splitedResponse[0], 'OK', {duration: 3000});
        const comAdded: Commande = {
          id: Number(splitedResponse[1]),
          date: splitedResponse[2],
          adresse: adr,
          films: this.filmsToNumberArray(),
          plats: this.panier.plats,
          prix: this.prixPanier
        };
        this.cs.addCom(comAdded);
        this.videPanier();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  POST(url, params: {[key: string]: string}): Promise<HttpResponse<string>> {
    const P = new HttpParams( {fromObject: params} );
    // console.log(params, P, P.toString());
    return this.http.post( url, P, {
      observe: 'response',
      responseType: 'text',
      headers: {'content-type': 'application/x-www-form-urlencoded'}
    }).toPromise();
  }

  private filmExisteDeja(id: number): boolean {
    return this.panier.films.filter( (f) => f.id === id).length !== 0;
  }

  private platExisteDeja(id: string): boolean {
    return this.panier.plats.filter( (p) => p.nom === id).length !== 0;
  }

  addFilm(film: MovieResponse): string {
    let msgSnackBar: string;
    if (!this.filmExisteDeja(film.id) || this.panier.films.length === 0) {
      this.panier.films.push(film);
      this.panierSubject.next(this.panier);
      msgSnackBar = 'Le film ' + film.title + ' a été ajouté au panier';
    } else {
      msgSnackBar = 'Le film ' + film.title + ' est déjà dans le panier';
    }
    return msgSnackBar;
  }

  addPlat(plat: Plat): string {
    if (!this.platExisteDeja(plat.nom) || this.panier.plats.length === 0) {
      this.panier.plats.push(plat);
      this.panierSubject.next(this.panier);
    } else {
      let isUpdated = false;
      let i = 0;
      while (!isUpdated && i < this.panier.plats.length) {
        if (this.panier.plats[i].nom === plat.nom) {
          isUpdated = true;
          this.panier.plats[i].quantite += plat.quantite;
        }
        i++;
      }
    }
    return 'x' + plat.quantite + ' ' + plat.nom + ' ajouté(s) au panier';
  }

  removeFilm(id: number) {
    this.panier.films = this.panier.films.filter( (f) => f.id !== id);
    this.panierSubject.next(this.panier);
  }

  removePlat(id: string) {
    this.panier.plats = this.panier.plats.filter((p) => p.nom !== id);
    this.panierSubject.next(this.panier);
  }

    peutPasserCommandeFilmPlat(): boolean {
    return (this.panier.plats.length >= 1 && this.panier.films.length >= 1);
  }

    peutPasserCommandeInfosClient(): boolean {
      const cli: Client = this.cs.getClient();
      return (cli.tel !== 'null' && cli.nom !== 'null' && cli.prenom !== 'null' && cli.mail !== 'null');
    }
}
