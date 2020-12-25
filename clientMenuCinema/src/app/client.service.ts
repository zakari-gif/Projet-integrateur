import { Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams, HttpResponse} from '@angular/common/http';
import {Adresse, Client} from './client-data/Client';
import {Router} from '@angular/router';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase/app';
import {BehaviorSubject} from 'rxjs';
import {Commande} from './client-data/Commande';
import {MovieResponse} from './tmdb-data/Movie';
import {TmdbService} from './tmdb.service';
@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private client: Client;
  private isCo: boolean;
  private mesComSubject = new BehaviorSubject<Commande[]>([]);
  readonly mesComObs = this.mesComSubject.asObservable();
  constructor(private http: HttpClient,
              private route: Router,
              private afAuth: AngularFireAuth,
              private tmdb: TmdbService) {
    // this.isCo = false;
    this.afAuth.user.subscribe(u => {
      if (u !== null) {
        const nomPrenom = u.displayName.split(' ');
        this.POST('/api/authentification',
          {
            userId: u.uid,
            nom: nomPrenom[1],
            prenom: nomPrenom[0],
            tel: u.phoneNumber,
            mail: u.email,
            photo: u.photoURL
          }
        ).then(
          (usr) => {
            this.setClient(usr.body as Client);
            this.isCo = true;
            if (window.location.pathname === '/') {
              this.route.navigate(['/films']);
            }
          },
          (err: HttpErrorResponse) => {
            console.log('Erreur : ' + err.message);
          }
        );
      }
    } );
  }

  get adrDisplay(): string {
    return this.client.adresse.numero + ' ' +
      this.client.adresse.rue + ', ' +
      this.client.adresse.cp + ' - ' +
      this.client.adresse.ville;
  }

  get isConnected(): boolean {
     // return this.storage.has('client');
    return this.isCo;
  }

  setClient(client: Client) {
    // this.storage.set('client', JSON.stringify(client));
    this.client = client;
    this.POST('/api/getComCli', {
      userId: client.id
    }).then(
      (reponse) => {
        /*const test: Commande[] = reponse.body as Commande[];
        test.forEach((c) => {
          console.log(this.testPromiseAll(c.films));
        });*/
         this.mesComSubject.next(reponse.body as Commande[]);
      },
      (err: HttpErrorResponse) => {
        console.log('Erreur : ' + err.message);
      }
    );
  }

  async promiseAllMoviesFromIds(ids: number[]) {
    return await Promise.all(this.promiseArrayFilms(ids));
  }

  promiseArrayFilms(ids: number[]): Promise<MovieResponse>[] { // : MovieResponse[] {
    const arrayPromise = [];
    ids.forEach(
      (id) => {
        arrayPromise.push(this.getMovie(id));
      }
    );
    return arrayPromise;
  }

  async getMovie(id: number): Promise<MovieResponse> {
    return await this.tmdb.getMovie(id, {language: 'fr'});
  }

  addCom(c: Commande) {
    const newVal: Commande[] = [...this.mesComSubject.value, c];
    this.mesComSubject.next(newVal);
  }
  getClient(): Client {
    // const cli: Client = JSON.parse(this.storage.get('client'));
    return this.client;
  }

  routingNotConnected() {
    if (!this.isConnected) {
      this.route.navigate(['/']);
    }
  }

  loginGoogle() {
    this.afAuth.signInWithPopup( new auth.GoogleAuthProvider());
  }

  logout() {
    this.afAuth.signOut().then(
      (v) => {
    this.client = undefined;
    this.isCo = false;
    this.route.navigate(['/']);
      }
    );
  }

  clientAdresseIsDefined(): boolean {
    const clientAdr: Adresse = this.getClient().adresse;
    return (
      clientAdr.numero !== 'null' &&
      clientAdr.rue !== 'null' &&
        clientAdr.ville !== 'null' &&
        clientAdr.cp !== 'null'
    );
  }

  POST(url, params: {[key: string]: string}): Promise<HttpResponse<object>> {
    const P = new HttpParams( {fromObject: params} );
    // console.log(params, P, P.toString());
    return this.http.post( url, P, {
      observe: 'response',
      responseType: 'json',
      headers: {'content-type': 'application/x-www-form-urlencoded'}
    }).toPromise();
  }
  }


