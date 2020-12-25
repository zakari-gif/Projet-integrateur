import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams, HttpResponse} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {Plat} from './client-data/Plat';
import {Carte} from './client-data/Carte';

@Injectable({
  providedIn: 'root'
})
export class ListePlatsService {

  private listePlatsSubject = new BehaviorSubject<Carte>({plats: []});
  readonly listePlatsObs = this.listePlatsSubject.asObservable();

  constructor(private http: HttpClient) { }

  init(): this {
    this.POST('/api/carte').then(
      (reponse) => {
        const carte: Carte = reponse.body as Carte;
        carte.plats.forEach(
          (p) => {
            p.quantite = 1;
          }
        );
        this.listePlatsSubject.next(carte);
      },
      (error: HttpErrorResponse) => {
        console.log('Erreur : ' + error.message);
      }
    );
    return this;
  }

  refreshList() {
    this.init();
  }

  POST(url): Promise<HttpResponse<object>> {
    return this.http.post( url, null, {
      observe: 'response',
      responseType: 'json',
      headers: {'content-type': 'application/x-www-form-urlencoded'}
    }).toPromise();
  }
}
