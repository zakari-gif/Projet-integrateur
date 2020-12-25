import {MovieResponse} from '../tmdb-data/Movie';
import {Plat} from './Plat';
import {Adresse} from './Client';

export interface Commande {
  id: number;
  date: string;
  prix: number;
  films: number[];
  plats: Plat[];
  adresse: Adresse;
}
