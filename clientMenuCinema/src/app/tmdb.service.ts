import { Injectable } from '@angular/core';
import {MovieQuery, MovieResponse} from './tmdb-data/Movie';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {PersonQuery, PersonResponse} from './tmdb-data/Person';
import {SearchMovieQuery, SearchMovieResponse} from './tmdb-data/searchMovie';
import {SearchPeopleQuery, SearchPeopleResponse} from './tmdb-data/SearchPeople';
import {TVQuery, TVResponse} from './tmdb-data/TV';
import {SearchTVQuery, SearchTVResponse} from './tmdb-data/SearchTV';
import {DiscoverMovieQuery, DiscoverMovieResponse} from './tmdb-data/DiscoverMovie';
import {BehaviorSubject} from 'rxjs';

const tmdbApi = 'https://api.themoviedb.org/3';
type HTTP_METHOD = 'GET' | 'POST' | 'DELETE' | 'PUT';

function AlxToObjectString(data?: object): {[key: string]: string} {
  const res = {};
  for (const k of Object.keys(data || {}) ) {
    const v = data[k];
    res[k] = typeof v === 'string' ? v : JSON.stringify(v);
  }
  return res;
}

@Injectable({
  providedIn: 'root'
})
export class TmdbService {
  private apiKey: string;
  private subjectDiscMovie = new BehaviorSubject<DiscoverMovieResponse>({});
  readonly obsDiscMovie = this.subjectDiscMovie.asObservable();

  async updatePageDiscMovie(newPage: number) {
    this.subjectDiscMovie.next(await this.discoverMovie({language: 'fr', include_adult: false, page: newPage}));
  }

  private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
    return this.http.get<T>( url, {
      observe: 'response',
      params: {...AlxToObjectString(data), api_key: this.apiKey}
    }).toPromise();
  }

  constructor(private http: HttpClient) {
  }

  init(key: string): this {
    this.apiKey = key;
    this.updatePageDiscMovie(1);
    return this;
  }

  // _______________________________________________________________________________________________________________________________________
  // Movies ________________________________________________________________________________________________________________________________
  // _______________________________________________________________________________________________________________________________________
  async getMovie(id: number, options?: MovieQuery): Promise<MovieResponse> {
    const url = `${tmdbApi}/movie/${id}`;
    const res = await this.get<MovieResponse>(url, options);
    return res.body;
  }

  async searchMovie(query: SearchMovieQuery): Promise<SearchMovieResponse> {
    const url = `${tmdbApi}/search/movie`;
    const res = await this.get<SearchMovieResponse>(url, query);
    return res.body;
  }

  async discoverMovie(query: DiscoverMovieQuery): Promise<DiscoverMovieResponse> {
    const url = `${tmdbApi}/discover/movie`;
    const res = await this.get<DiscoverMovieResponse>(url, query);
    return res.body;
  }

  // _______________________________________________________________________________________________________________________________________
  // Person / People _______________________________________________________________________________________________________________________
  // _______________________________________________________________________________________________________________________________________
  async getPerson(id: number, options?: PersonQuery): Promise<PersonResponse> {
    const url = `${tmdbApi}/person/${id}`;
    const res = await this.get<PersonResponse>(url, options);
    return res.body;
  }

  async searchPerson(query: SearchPeopleQuery): Promise<SearchPeopleResponse> {
    const url = `${tmdbApi}/search/person`;
    const res = await this.get<SearchPeopleResponse>(url, query);
    return res.body;
  }

  // _______________________________________________________________________________________________________________________________________
  // TV ____________________________________________________________________________________________________________________________________
  // _______________________________________________________________________________________________________________________________________
  async getTV(id: number, options?: TVQuery): Promise<TVResponse> {
    const url = `${tmdbApi}/tv/${id}`;
    const res = await this.get<TVResponse>(url, options);
    return res.body;
  }

  async searchTV(query: SearchTVQuery): Promise<SearchTVResponse> {
    const url = `${tmdbApi}/search/tv`;
    const res = await this.get<SearchTVResponse>(url, query);
    return res.body;
  }

}
