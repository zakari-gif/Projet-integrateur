import {MovieResponse} from '../tmdb-data/Movie';
import {Plat} from './Plat';
import {MatSnackBar} from '@angular/material/snack-bar';

export interface Panier {
  films: MovieResponse[];
  plats: Plat[];
}
