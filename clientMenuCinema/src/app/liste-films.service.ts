import { Injectable } from '@angular/core';
import {TmdbService} from './tmdb.service';
import {BehaviorSubject} from 'rxjs';
import {DiscoverMovieResponse} from './tmdb-data/DiscoverMovie';
import {SearchMovieResponse} from './tmdb-data/searchMovie';

@Injectable({
  providedIn: 'root'
})
export class ListeFilmsService {

  private listeFilmsSubject = new BehaviorSubject<DiscoverMovieResponse|SearchMovieResponse>({});
  readonly listeFilmsObs = this.listeFilmsSubject.asObservable();
  private isDisc: boolean;
  private searchQuery: string;
  private memorNbPageDisc: number;
  constructor(private tmdb: TmdbService) { }

  init(): this {
    this.updatePageDiscMovie(1);
    return this;
  }

  async updatePageDiscMovie(newPage: number) {
    this.isDisc = true;
    this.searchQuery = '';
    this.memorNbPageDisc = newPage;
    this.listeFilmsSubject.next(await this.tmdb.discoverMovie({language: 'fr', include_adult: false, page: newPage}));
  }

  async updatePageSearchMovie(newPage: number, search: string) {
    this.isDisc = false;
    this.searchQuery = search;
    this.listeFilmsSubject.next(await this.tmdb.searchMovie({query: search, language: 'fr', include_adult: false, page: newPage}));
  }

  get isDiscover(): boolean {
    return this.isDisc;
  }

  get query(): string {
    return this.searchQuery;
  }

  get pageDisc(): number {
    return this.memorNbPageDisc;
  }
}
